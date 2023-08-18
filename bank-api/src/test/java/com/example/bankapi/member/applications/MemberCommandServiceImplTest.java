package com.example.bankapi.member.applications;

import com.example.bankapi.member.applications.dto.request.CreateMemberServiceRequest;
import com.example.bankapi.member.applications.dto.response.CreateMemberServiceResponse;
import com.example.bankmember.applications.FakeMemberCommandRepository;
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
        memberCommandRepository = new FakeMemberCommandRepository();
        memberCommandService = new MemberCommandService(memberCommandRepository);
    }

    @DisplayName("회원 가입 성공")
    @Test
    void create() {
        CreateMemberServiceRequest request = new CreateMemberServiceRequest("홍길동");

        CreateMemberServiceResponse result = memberCommandService.create(request);

        assertThat(result.getName()).isEqualTo(request.getName());
    }

    @DisplayName("회원명 null 입력하면 에러")
    @Test
    void nullNameInput() {
        CreateMemberServiceRequest request = new CreateMemberServiceRequest(null);

        assertThatThrownBy(() -> memberCommandService.create(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원명 빈값 입력하면 에러")
    @Test
    void emptyNameInput() {
        CreateMemberServiceRequest request = new CreateMemberServiceRequest("");

        assertThatThrownBy(() -> memberCommandService.create(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}