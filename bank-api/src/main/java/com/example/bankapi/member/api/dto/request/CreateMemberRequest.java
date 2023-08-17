package com.example.bankapi.member.api.dto.request;

import com.example.bankapi.member.applications.dto.request.CreateMemberServiceRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMemberRequest {
    @NotBlank(message = "이름은 필수값 입니다.")
    private String name;

    public CreateMemberRequest(String name) {
        this.name = name;
    }

    public CreateMemberServiceRequest toServiceRequest() {
        return new CreateMemberServiceRequest(name);
    }
}
