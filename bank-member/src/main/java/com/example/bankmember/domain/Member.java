package com.example.bankmember.domain;

import com.example.bankcommon.domain.Name;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    private final Long id;
    private final Name name;
    private final Role role;
    private final MemberState state;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public Member(Long id, Name name, Role role, MemberState state, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        if (Objects.isNull(role)) {
            throw new IllegalArgumentException("권한은 필수값 입니다.");
        }

        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("상태는 필수값 입니다.");
        }

        this.id = id;
        this.name = name;
        this.role = role;
        this.state = state;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
