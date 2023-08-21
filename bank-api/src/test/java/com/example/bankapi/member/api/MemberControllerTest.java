package com.example.bankapi.member.api;

import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankapi.global.auth.jwt.JwtProvider;
import com.example.bankapi.global.handler.AuthMemberArgument;
import com.example.bankapi.global.model.MemberPrincipal;
import com.example.bankapi.member.api.dto.request.LoginRequest;
import com.example.bankapi.member.api.dto.request.SignupRequest;
import com.example.bankapi.member.applications.MemberCommandService;
import com.example.bankapi.member.applications.MemberQueryService;
import com.example.bankapi.member.applications.dto.response.LoginServiceResponse;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.example.bankmember.domain.MemberState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberCommandService memberCommandService;

    @MockBean
    private MemberQueryService memberQueryService;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private AuthMemberArgument authMemberArgument;

    private static final String NAME = "홍길동";

    @DisplayName("회원가입 성공")
    @Test
    void signup() throws Exception {
        // given
        SignupRequest request = new SignupRequest(NAME);

        when(memberCommandService.signup(any())).thenReturn(SignupServiceResponse.builder().build());

        mockMvc.perform(
                post("/api/v1/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("회원명이 null 이면 에러")
    @Test
    void nullName() throws Exception {
        SignupRequest request = new SignupRequest(null);

        mockMvc.perform(
                        post("/api/v1/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원명이 빈값이면 에러")
    @Test
    void notInputName() throws Exception {
        SignupRequest request = new SignupRequest("");

        mockMvc.perform(
                        post("/api/v1/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("로그인 성공")
    @Test
    void login() throws Exception {
        // given
        LoginRequest request = new LoginRequest(NAME);

        when(memberQueryService.login(any())).thenReturn(LoginServiceResponse.builder().build());

        mockMvc.perform(
                        post("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("회원명이 null 이면 에러")
    @Test
    void nullNameLogin() throws Exception {
        LoginRequest request = new LoginRequest(null);

        mockMvc.perform(
                        post("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원명이 빈값이면 에러")
    @Test
    void notInputNameLogin() throws Exception {
        LoginRequest request = new LoginRequest("");

        mockMvc.perform(
                        post("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("로그인 회원 정보 조회")
    @Test
    void getMember() throws Exception {
        MemberDetails memberDetails = new MemberPrincipal(1L, NAME, MemberState.ACTIVITY);

        when(authMemberArgument.supportsParameter(any())).thenReturn(true);
        when(authMemberArgument.resolveArgument(any(), any(), any(), any())).thenReturn(memberDetails);

        // given
        mockMvc.perform(
                        get("/api/v1/members")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.state").value(MemberState.ACTIVITY.name()));
    }
}