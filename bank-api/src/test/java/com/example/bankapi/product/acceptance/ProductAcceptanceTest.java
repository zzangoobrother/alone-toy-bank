package com.example.bankapi.product.acceptance;

import com.example.bankapi.AcceptanceTest;
import com.example.bankapi.member.acceptance.MemberSteps;
import com.example.bankproduct.domain.ProductType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    void setup() {
        MemberSteps.회원_가입_요청("홍길동");
    }

    @DisplayName("여신 상품을 등록한다.")
    @Test
    void createLoan() {
        // when
        String token = MemberSteps.로그인_되어_있음("홍길동");

        ExtractableResponse<Response> response = ProductSteps.상품_등록("대출", ProductType.LOAN, token);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("수신 상품을 등록한다.")
    @Test
    void createDeposit() {
        // when
        String token = MemberSteps.로그인_되어_있음("홍길동");

        ExtractableResponse<Response> response = ProductSteps.상품_등록("입출금", ProductType.DEPOSIT, token);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
