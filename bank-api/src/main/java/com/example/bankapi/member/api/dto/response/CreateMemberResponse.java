package com.example.bankapi.member.api.dto.response;

import com.example.bankapi.member.applications.dto.response.CreateMemberServiceResponse;
import com.example.bankmember.domain.MemberState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMemberResponse {
    private String name;
    private MemberState state;

    @Builder
    public CreateMemberResponse(String name, MemberState state) {
        this.name = name;
        this.state = state;
    }

    public static CreateMemberResponse toResponse(CreateMemberServiceResponse response) {
        return CreateMemberResponse.builder()
                .name(response.getName())
                .state(response.getState())
                .build();
    }
}
