package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.SignupServiceRequest;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankmember.applications.port.MemberCommandRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberSignupService {

    private final MemberCommandRepository memberCommandRepository;

    public MemberSignupService(MemberCommandRepository memberCommandRepository) {
        this.memberCommandRepository = memberCommandRepository;
    }

    @Transactional
    public SignupServiceResponse signup(SignupServiceRequest request) {
        Member member = Member.builder()
                .name(Name.newInstance(request.getName()))
                .state(MemberState.ACTIVITY)
                .build();

        Member saveMember = memberCommandRepository.save(member);
        return SignupServiceResponse.builder()
                .name(saveMember.getName().getName())
                .state(saveMember.getState())
                .build();
    }
}
