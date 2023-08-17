package com.example.bankmember.applications;

import com.example.bankmember.domain.Member;

public interface MemberCommandRepository {
    Member save(Member member);
}
