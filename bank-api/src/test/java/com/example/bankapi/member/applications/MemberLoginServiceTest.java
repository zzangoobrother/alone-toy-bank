package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.LoginServiceRequest;
import com.example.bankapi.member.applications.dto.response.LoginServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankmember.applications.FakeMemberRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberLoginServiceTest {

    private FakeMemberRepository memberQueryRepository;

    private MemberLoginService memberLoginService;

    @BeforeEach
    void init() {
        memberQueryRepository = new FakeMemberRepository();
        memberLoginService = new MemberLoginService(memberQueryRepository);

        Member member = Member.builder().name(Name.newInstance("홍길동")).state(MemberState.ACTIVITY).build();
        memberQueryRepository.save(member);
    }

    @DisplayName("로그인 성공")
    @Test
    void login() {
        LoginServiceRequest request = new LoginServiceRequest("홍길동");

        LoginServiceResponse result = memberLoginService.login(request);

        assertThat(request.getName()).isEqualTo(result.getName());
    }
}