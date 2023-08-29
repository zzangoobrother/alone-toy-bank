package com.example.bankapi.product.acceptance;

import com.example.bankapi.AcceptanceTest;
import com.example.bankapi.member.acceptance.MemberSteps;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAcceptanceTest extends AcceptanceTest {

    private static final String NAME = "홍길동";
    private static final String LOAN_NAME = "대출";
    private static final String DEPOSIT_NAME = "입출금";

    private String token;

    @BeforeEach
    void setup() {
        MemberSteps.회원_가입_요청("홍길동");
        token = MemberSteps.로그인_되어_있음(NAME);
    }

    @DisplayName("여신 상품을 등록한다.")
    @Test
    void createLoan() {
        // when
        ExtractableResponse<Response> response = ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("수신 상품을 등록한다.")
    @Test
    void createDeposit() {
        // when
        ExtractableResponse<Response> response = ProductSteps.상품_등록(DEPOSIT_NAME, ProductType.DEPOSIT, token);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("상품을 전체 조회한다.")
    @Test
    void getProducts() {
        // when
        ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token);
        ProductSteps.상품_등록(DEPOSIT_NAME, ProductType.DEPOSIT, token);

        ExtractableResponse<Response> response = ProductSteps.상품_전체_조회(token);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("name")).contains(LOAN_NAME, DEPOSIT_NAME);
        assertThat(response.jsonPath().getList("type")).contains(ProductType.LOAN, ProductType.DEPOSIT);
        assertThat(response.jsonPath().getList("state")).contains(ProductState.ACTIVITY, ProductState.ACTIVITY);
    }

    @DisplayName("상품을 단건 조회한다.")
    @Test
    void getProduct() {
        // when
        ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token);

        ExtractableResponse<Response> response = ProductSteps.상품_단건_조회(1L, token);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo(LOAN_NAME);
        assertThat(response.jsonPath().getString("type")).isEqualTo(ProductType.LOAN);
        assertThat(response.jsonPath().getString("state")).isEqualTo(ProductState.ACTIVITY);
    }
}
