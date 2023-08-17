package com.example.bankapi.member.api;

import com.example.bankapi.member.api.dto.request.CreateMemberRequest;
import com.example.bankapi.member.api.dto.response.CreateMemberResponse;
import com.example.bankapi.member.applications.MemberCommandService;
import com.example.bankapi.member.applications.dto.response.CreateMemberServiceResponse;
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
    @PostMapping("/api/members")
    public CreateMemberResponse create(@Valid @RequestBody CreateMemberRequest request) {
        CreateMemberServiceResponse createMemberServiceResponse = memberCommandService.create(request.toServiceRequest());
        return CreateMemberResponse.toResponse(createMemberServiceResponse);
    }
}
