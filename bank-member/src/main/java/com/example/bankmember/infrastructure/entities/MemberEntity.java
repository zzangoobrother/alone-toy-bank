package com.example.bankmember.infrastructure.entities;

import com.example.bankcommon.domain.BaseEntity;
import com.example.bankcommon.domain.Name;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    private MemberState state;

    @Builder
    public MemberEntity(Name name, MemberState state) {
        this.name = name;
        this.state = state;
    }

    public Member toModel() {
        return Member.builder()
                .id(getId())
                .name(getName())
                .state(getState())
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }

    public static MemberEntity toEntity(Member member) {
        MemberEntity memberEntity = MemberEntity.builder()
                .name(member.getName())
                .state(member.getState())
                .build();

        memberEntity.id = member.getId();
        memberEntity.createdAt = member.getCreatedAt();
        memberEntity.modifiedAt = member.getModifiedAt();

        return memberEntity;
    }
}
