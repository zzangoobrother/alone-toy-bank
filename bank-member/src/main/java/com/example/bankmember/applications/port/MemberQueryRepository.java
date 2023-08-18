package com.example.bankmember.applications.port;

import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;

public interface MemberQueryRepository {
    Member getMember(String name);

    Member getMember(String name, MemberState memberState);
}
