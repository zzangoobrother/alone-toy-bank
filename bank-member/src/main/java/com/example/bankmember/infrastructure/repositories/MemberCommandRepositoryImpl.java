package com.example.bankmember.infrastructure.repositories;

import com.example.bankmember.applications.MemberCommandRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.infrastructure.entities.MemberEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MemberCommandRepositoryImpl implements MemberCommandRepository {

    private final JpaMemberRepository jpaMemberRepository;

    public MemberCommandRepositoryImpl(JpaMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = MemberEntity.fromEntity(member);
        return jpaMemberRepository.save(memberEntity).toModel();
    }
}
