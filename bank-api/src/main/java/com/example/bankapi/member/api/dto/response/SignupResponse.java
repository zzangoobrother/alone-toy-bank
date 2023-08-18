package com.example.bankapi.member.api.dto.response;

import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.example.bankmember.domain.MemberState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupResponse {
    private String name;
    private MemberState state;

    @Builder
    public SignupResponse(String name, MemberState state) {
        this.name = name;
        this.state = state;
    }

    public static SignupResponse toResponse(SignupServiceResponse response) {
        return SignupResponse.builder()
                .name(response.getName())
                .state(response.getState())
                .build();
    }
}
