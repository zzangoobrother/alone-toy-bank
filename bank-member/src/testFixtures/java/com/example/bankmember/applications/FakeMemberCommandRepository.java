package com.example.bankmember.applications;

import com.example.bankcommon.entity.Name;
import com.example.bankmember.applications.port.MemberCommandRepository;
import com.example.bankmember.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMemberCommandRepository implements MemberCommandRepository {
    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Member> members = new ArrayList<>();

    @Override
    public Member save(Member member) {

        if (member.getId() == null || member.getId() == 0) {
            Member newMember = Member.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .name(Name.newInstance(member.getName().getName()))
                    .state(member.getState())
                    .createdAt(member.getCreatedAt())
                    .modifiedAt(member.getModifiedAt())
                    .build();

            members.add(newMember);
            return newMember;
        }

        members.removeIf(m -> Objects.equals(m.getId(), member.getId()));
        members.add(member);
        return member;
    }
}