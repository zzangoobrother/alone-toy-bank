package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.SignupServiceRequest;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankmember.applications.port.MemberCommandRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import com.example.bankmember.domain.Role;
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
    public SignupServiceResponse signup(SignupServiceRequest request, Role role) {
        Member member = Member.builder()
                .name(Name.newInstance(request.getName()))
                .role(role)
                .state(MemberState.ACTIVITY)
                .build();

        Member saveMember = memberCommandRepository.save(member);
        return SignupServiceResponse.builder()
                .name(saveMember.getName().getName())
                .role(saveMember.getRole())
                .state(saveMember.getState())
                .build();
    }
}
