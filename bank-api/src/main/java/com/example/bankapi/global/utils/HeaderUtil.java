package com.example.bankapi.global.utils;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

public class HeaderUtil {
    private final static String TOKEN_PREFIX = "Bearer ";

    public static Optional<String> getToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(authorization)) {
            return Optional.empty();
        }

        if (authorization.startsWith(TOKEN_PREFIX)) {
            return Optional.of(authorization.substring(TOKEN_PREFIX.length()));
        }

        return Optional.empty();
    }

    public static HttpHeaders getTokenHeader(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);

        return httpHeaders;
    }
}
