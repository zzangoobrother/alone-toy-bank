package com.example.bankapi.global.filter;

import com.example.bankapi.global.auth.AuthThreadLocalContext;
import com.example.bankapi.global.auth.jwt.JwtProvider;
import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankapi.global.auth.MemberDetailsService;
import com.example.bankapi.global.exception.ApiErrorCode;
import com.example.bankapi.global.exception.ErrorResponse;
import com.example.bankapi.global.exception.InvalidTokenException;
import com.example.bankapi.global.utils.HeaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AuthFilter implements Filter {

    private List<String> excludeUrl = new ArrayList<>();

    private final MemberDetailsService memberDetailsService;
    private final JwtProvider jwtProvider;

    public AuthFilter(MemberDetailsService memberDetailsService, JwtProvider jwtProvider) {
        this.memberDetailsService = memberDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrl = createExcludeUrlList();
    }

    private List<String> createExcludeUrlList() {
        return Arrays.asList("/api/v1/signup", "/api/v1/login");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        try {
            String path = httpServletRequest.getServletPath();
            log.info(path);
            if (!excludeUrl.contains(path)) {
                String token = HeaderUtil.getToken(httpServletRequest).orElseThrow(() -> new InvalidTokenException(ApiErrorCode.LOGIN_INPUT_VALUE));

                if (jwtProvider.validate(token)) {
                    log.error("토큰 만료");
                    throw new InvalidTokenException(ApiErrorCode.EFFECTIVE_TOKEN_VALUE);
                }

                String name = jwtProvider.getExpiredTokenClaims(token).getSubject();
                MemberDetails memberDetails = memberDetailsService.getMemberDetails(name);
                log.info("threadlocal 데이터 넣기");
                AuthThreadLocalContext.setMemberDetails(memberDetails);
            }

            chain.doFilter(request, response);

            log.info("threadlocal 데이터 삭제");
            AuthThreadLocalContext.remove();
        } catch (InvalidTokenException e) {
            errorResponse(response, e);
        }
    }

    private void errorResponse(ServletResponse response, InvalidTokenException e) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try {
            ApiErrorCode errorcode = ApiErrorCode.EFFECTIVE_TOKEN_VALUE;
            response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(errorcode.getMessage(), errorcode.getStatus(), errorcode.getCode())));
        } catch (IOException ex) {
            log.error("인증 에러 response 변환중 에러 발생");
            throw new RuntimeException(e);
        }
    }


}
