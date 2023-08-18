package com.example.bankapi.member.applications.dto.response;

import com.example.bankmember.domain.MemberState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupServiceResponse {
    private String name;
    private MemberState state;

    @Builder
    public SignupServiceResponse(String name, MemberState state) {
        this.name = name;
        this.state = state;
    }
}
