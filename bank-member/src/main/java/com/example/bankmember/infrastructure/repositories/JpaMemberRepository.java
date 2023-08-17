package com.example.bankmember.infrastructure.repositories;

import com.example.bankmember.infrastructure.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
