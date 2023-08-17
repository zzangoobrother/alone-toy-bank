package com.example.bankapi.member.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberAcceptanceTest extends AcceptanceTest {

    private static final String NAME = "홍길동";

    @DisplayName("회원 가입을 한다.")
    @Test
    void create() {
        // when
        ExtractableResponse<Response> response = MemberSteps.회원_가입_요청(NAME);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}