package com.example.bankapi.member.api.dto.response;

import com.example.bankmember.domain.MemberState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String name;
    private MemberState state;

    @Builder
    public MemberResponse(String name, MemberState state) {
        this.name = name;
        this.state = state;
    }
}
