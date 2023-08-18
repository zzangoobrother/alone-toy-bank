package com.example.bankapi.member.applications.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupServiceRequest {
    private String name;

    public SignupServiceRequest(String name) {
        this.name = name;
    }
}
