package com.example.bankapi.member.api;

import com.example.bankapi.global.annotation.AuthMemberPrincipal;
import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankapi.global.auth.jwt.JwtProvider;
import com.example.bankapi.member.api.dto.request.LoginRequest;
import com.example.bankapi.member.api.dto.request.SignupRequest;
import com.example.bankapi.member.api.dto.response.LoginResponse;
import com.example.bankapi.member.api.dto.response.MemberResponse;
import com.example.bankapi.member.api.dto.response.SignupResponse;
import com.example.bankapi.member.applications.MemberCommandService;
import com.example.bankapi.member.applications.MemberQueryService;
import com.example.bankapi.member.applications.dto.response.LoginServiceResponse;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final JwtProvider jwtProvider;

    public MemberController(MemberCommandService memberCommandService, MemberQueryService memberQueryService, JwtProvider jwtProvider) {
        this.memberCommandService = memberCommandService;
        this.memberQueryService = memberQueryService;
        this.jwtProvider = jwtProvider;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest request) {
        SignupServiceResponse signupServiceResponse = memberCommandService.signup(request.toServiceRequest());
        return SignupResponse.toResponse(signupServiceResponse);
    }

    @PostMapping("/api/v1/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        LoginServiceResponse loginServiceResponse = memberQueryService.login(request.toServiceRequest());
        String token = jwtProvider.createToken(loginServiceResponse.getName());

        return LoginResponse.toResponse(token);
    }

    @GetMapping("/api/v1/members")
    public MemberResponse getMember(@AuthMemberPrincipal MemberDetails memberDetails) {
        return new MemberResponse(memberDetails.getName(), memberDetails.getState());
    }
}
