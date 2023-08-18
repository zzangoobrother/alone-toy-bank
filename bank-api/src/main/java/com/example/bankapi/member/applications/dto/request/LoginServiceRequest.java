package com.example.bankapi.member.applications.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginServiceRequest {
    private String name;

    public LoginServiceRequest(String name) {
        this.name = name;
    }
}
