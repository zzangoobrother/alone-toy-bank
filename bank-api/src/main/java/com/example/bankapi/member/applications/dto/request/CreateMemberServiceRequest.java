package com.example.bankapi.member.applications.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMemberServiceRequest {
    private String name;

    public CreateMemberServiceRequest(String name) {
        this.name = name;
    }
}
