package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.SignupServiceRequest;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.example.bankcommon.entity.Name;
import com.example.bankmember.applications.port.MemberCommandRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import org.springframework.stereotype.Service;

@Service
public class MemberCommandService {

    private final MemberCommandRepository memberCommandRepository;

    public MemberCommandService(MemberCommandRepository memberCommandRepository) {
        this.memberCommandRepository = memberCommandRepository;
    }

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
