package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.LoginServiceRequest;
import com.example.bankapi.member.applications.dto.response.LoginServiceResponse;
import com.example.bankcommon.entity.Name;
import com.example.bankmember.applications.FakeMemberRepository;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberQueryServiceTest {

    private FakeMemberRepository memberQueryRepository;

    private MemberQueryService memberQueryService;

    @BeforeEach
    void init() {
        memberQueryRepository = new FakeMemberRepository();
        memberQueryService = new MemberQueryService(memberQueryRepository);

        Member member = Member.builder().name(Name.newInstance("홍길동")).state(MemberState.ACTIVITY).build();
        memberQueryRepository.save(member);
    }

    @DisplayName("로그인 성공")
    @Test
    void login() {
        LoginServiceRequest request = new LoginServiceRequest("홍길동");

        LoginServiceResponse result = memberQueryService.login(request);

        assertThat(request.getName()).isEqualTo(result.getName());
    }
}