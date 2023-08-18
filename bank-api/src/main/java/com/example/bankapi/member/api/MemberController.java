package com.example.bankapi.member.api;

import com.example.bankapi.member.api.dto.request.SignupRequest;
import com.example.bankapi.member.api.dto.response.SignupResponse;
import com.example.bankapi.member.applications.MemberCommandService;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MemberController {

    private final MemberCommandService memberCommandService;

    public MemberController(MemberCommandService memberCommandService) {
        this.memberCommandService = memberCommandService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest request) {
        SignupServiceResponse signupServiceResponse = memberCommandService.signup(request.toServiceRequest());
        return SignupResponse.toResponse(signupServiceResponse);
    }
}
