package com.example.bankapi.member.applications.dto.response;

import com.example.bankmember.domain.MemberState;
import com.example.bankmember.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupServiceResponse {
    private String name;
    private Role role;
    private MemberState state;

    @Builder
    public SignupServiceResponse(String name, Role role, MemberState state) {
        this.name = name;
        this.role = role;
        this.state = state;
    }
}
