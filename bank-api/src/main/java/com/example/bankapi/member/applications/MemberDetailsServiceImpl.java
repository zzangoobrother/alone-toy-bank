package com.example.bankapi.member.applications;

import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankapi.global.auth.MemberDetailsService;
import com.example.bankapi.global.model.MemberPrincipal;
import com.example.bankmember.applications.port.MemberQueryRepository;
import com.example.bankmember.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberDetailsServiceImpl implements MemberDetailsService {

    private final MemberQueryRepository memberQueryRepository;

    public MemberDetailsServiceImpl(MemberQueryRepository memberQueryRepository) {
        this.memberQueryRepository = memberQueryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDetails getMemberDetails(String name) {
        Member member = memberQueryRepository.getMember(name);
        return new MemberPrincipal(member.getId(), member.getName().getName(), member.getState(), member.getRole());
    }
}
