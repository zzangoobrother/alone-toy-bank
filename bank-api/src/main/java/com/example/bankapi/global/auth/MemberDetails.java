package com.example.bankapi.global.auth;

import com.example.bankmember.domain.MemberState;
import com.example.bankmember.domain.Role;

public interface MemberDetails {

    Long getMemberId();
    String getName();
    MemberState getState();
    Role getRole();
}
