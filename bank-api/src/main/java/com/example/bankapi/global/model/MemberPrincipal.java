package com.example.bankapi.global.model;

import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankmember.domain.MemberState;

public class MemberPrincipal implements MemberDetails {

    private final Long memberId;
    private final String name;
    private final MemberState memberState;

    public MemberPrincipal(Long memberId, String name, MemberState memberState) {
        this.memberId = memberId;
        this.name = name;
        this.memberState = memberState;
    }

    @Override
    public Long getMemberId() {
        return this.memberId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public MemberState getState() {
        return memberState;
    }
}
