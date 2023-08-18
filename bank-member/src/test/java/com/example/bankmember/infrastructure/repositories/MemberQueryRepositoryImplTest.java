package com.example.bankmember.infrastructure.repositories;

import com.example.bankcommon.entity.Name;
import com.example.bankmember.domain.Member;
import com.example.bankmember.domain.MemberState;
import com.example.bankmember.infrastructure.entities.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberQueryRepositoryImplTest {

    @Mock
    private JpaMemberRepository memberRepository;

    @InjectMocks
    private MemberQueryRepositoryImpl memberQueryRepository;

    @DisplayName("입력된 이름과 같은 회원 조회")
    @Test
    void findMemberByName() {
        MemberEntity memberEntity = MemberEntity.builder()
                .name(Name.newInstance("홍길동"))
                .state(MemberState.ACTIVITY)
                .build();

        when(memberRepository.findByName(any())).thenReturn(Optional.of(memberEntity));

        Member findMember = memberQueryRepository.getMember("홍길동");

        assertThat(findMember.getName()).isEqualTo(memberEntity.getName());
    }

    @DisplayName("입력된 같은 이름과 활성 상태인 회원 조회")
    @Test
    void findMemberByNameAndActivity() {
        MemberEntity memberEntity = MemberEntity.builder()
                .name(Name.newInstance("홍길동"))
                .state(MemberState.ACTIVITY)
                .build();

        when(memberRepository.findByNameAndState(any(), any())).thenReturn(Optional.of(memberEntity));

        Member findMember = memberQueryRepository.getMember("홍길동", MemberState.ACTIVITY);

        assertThat(findMember.getName()).isEqualTo(memberEntity.getName());
    }
}