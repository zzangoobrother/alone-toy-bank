package com.example.bankapi.member.applications.dto.response;

import com.example.bankmember.domain.MemberState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMemberServiceResponse {
    private String name;
    private MemberState state;

    @Builder
    public CreateMemberServiceResponse(String name, MemberState state) {
        this.name = name;
        this.state = state;
    }
}
