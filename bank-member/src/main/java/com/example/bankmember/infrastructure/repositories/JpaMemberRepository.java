package com.example.bankmember.infrastructure.repositories;

import com.example.bankcommon.entity.Name;
import com.example.bankmember.domain.MemberState;
import com.example.bankmember.infrastructure.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByName(Name name);

    Optional<MemberEntity> findByNameAndState(Name name, MemberState memberState);
}
