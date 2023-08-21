package com.example.bankapi.global.auth;

import com.example.bankmember.domain.MemberState;

public interface MemberDetails {

    Long getMemberId();
    String getName();
    MemberState getState();
}
