package com.example.bankapi.member.api.dto.request;

import com.example.bankapi.member.applications.dto.request.SignupServiceRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupRequest {
    @NotBlank(message = "이름은 필수값 입니다.")
    private String name;

    public SignupRequest(String name) {
        this.name = name;
    }

    public SignupServiceRequest toServiceRequest() {
        return new SignupServiceRequest(name);
    }
}
