package com.example.bankmember.infrastructure.repositories;

import com.example.bankcommon.domain.Name;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import com.example.bankmember.infrastructure.entities.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberCommandRepositoryImplTest {

    @Mock
    private JpaMemberRepository memberRepository;

    @InjectMocks
    private MemberCommandRepositoryImpl memberCommandRepository;

    @DisplayName("회원 저장")
    @Test
    void create() {
        MemberEntity memberEntity = MemberEntity.builder()
                .name(Name.newInstance("홍길동"))
                .state(MemberState.ACTIVITY)
                .build();

        Member member = memberEntity.toModel();

        when(memberRepository.save(any())).thenReturn(memberEntity);

        Member saveMember = memberCommandRepository.save(member);

        assertThat(saveMember.getName()).isEqualTo(member.getName());
    }
}