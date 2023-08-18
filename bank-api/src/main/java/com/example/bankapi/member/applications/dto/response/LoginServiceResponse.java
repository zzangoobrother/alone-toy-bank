package com.example.bankapi.member.applications.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginServiceResponse {
    private String name;

    @Builder
    public LoginServiceResponse(String name) {
        this.name = name;
    }
}
