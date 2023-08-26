package com.example.bankmember.infrastructure.repositories;

import com.example.bankcommon.domain.Name;
import com.example.bankcommon.exception.EntityNotFoundException;
import com.example.bankcommon.exception.ErrorCode;
import com.example.bankmember.applications.port.MemberQueryRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JpaMemberRepository memberRepository;

    public MemberQueryRepositoryImpl(JpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(String name) {
        return memberRepository.findByName(Name.newInstance(name)).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND)).toModel();
    }

    @Override
    public Member getMember(String name, MemberState memberState) {
        return memberRepository.findByNameAndState(Name.newInstance(name), MemberState.ACTIVITY).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND)).toModel();
    }
}
