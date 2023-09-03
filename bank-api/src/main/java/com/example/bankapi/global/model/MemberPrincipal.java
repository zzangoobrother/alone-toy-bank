package com.example.bankapi.global.model;

import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankmember.domain.MemberState;
import com.example.bankmember.domain.Role;

public class MemberPrincipal implements MemberDetails {

    private final Long memberId;
    private final String name;
    private final MemberState memberState;
    private final Role role;

    public MemberPrincipal(Long memberId, String name, MemberState memberState, Role role) {
        this.memberId = memberId;
        this.name = name;
        this.memberState = memberState;
        this.role = role;
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

    @Override
    public Role getRole() {
        return role;
    }
}
