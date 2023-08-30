package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.LoginServiceRequest;
import com.example.bankapi.member.applications.dto.response.LoginServiceResponse;
import com.example.bankmember.applications.port.MemberQueryRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class MemberLoginService {

    private final MemberQueryRepository memberQueryRepository;

    public MemberLoginService(MemberQueryRepository memberQueryRepository) {
        this.memberQueryRepository = memberQueryRepository;
    }

    @Transactional(readOnly = true)
    public LoginServiceResponse login(LoginServiceRequest request) {
        Member member = memberQueryRepository.getMember(request.getName(), MemberState.ACTIVITY);

        return new LoginServiceResponse(member.getName().getName());
    }
}
