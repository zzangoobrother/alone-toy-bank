package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.SignupServiceRequest;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.example.bankmember.applications.FakeMemberRepository;
import com.example.bankmember.applications.port.MemberCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberCommandServiceImplTest {

    private MemberCommandRepository memberCommandRepository;
    private MemberCommandService memberCommandService;

    @BeforeEach
    void init() {
        memberCommandRepository = new FakeMemberRepository();
        memberCommandService = new MemberCommandService(memberCommandRepository);
    }

    @DisplayName("회원 가입 성공")
    @Test
    void signup() {
        SignupServiceRequest request = new SignupServiceRequest("홍길동");

        SignupServiceResponse result = memberCommandService.signup(request);

        assertThat(result.getName()).isEqualTo(request.getName());
    }

    @DisplayName("회원명 null 입력하면 에러")
    @Test
    void nullNameInput() {
        SignupServiceRequest request = new SignupServiceRequest(null);

        assertThatThrownBy(() -> memberCommandService.signup(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원명 빈값 입력하면 에러")
    @Test
    void emptyNameInput() {
        SignupServiceRequest request = new SignupServiceRequest("");

        assertThatThrownBy(() -> memberCommandService.signup(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}