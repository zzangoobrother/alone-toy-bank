package com.example.bankmember.applications.port;

import com.example.bankmember.domain.Member;

public interface MemberCommandRepository {
    Member save(Member member);
}
