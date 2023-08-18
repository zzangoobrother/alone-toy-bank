package com.example.bankapi.member.api.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String token;

    private LoginResponse(String token) {
        this.token = token;
    }

    public static LoginResponse toResponse(String token) {
        return new LoginResponse(token);
    }
}
