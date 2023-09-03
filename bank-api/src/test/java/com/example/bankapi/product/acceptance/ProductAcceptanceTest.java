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
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProductAcceptanceTest extends AcceptanceTest {

    private static final String NAME = "홍길동";
    private static final String LOAN_NAME = "대출";
    private static final String DEPOSIT_NAME = "입출금";

    private String token;

    @BeforeEach
    void setup() {
        MemberSteps.관리자_회원_가입_요청("홍길동");
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

    @DisplayName("권한이 없는 회원이 상품을 등록하면 안된다.")
    @Test
    void notAuthorization() {
        // given
        MemberSteps.회원_가입_요청("이순신");
        token = MemberSteps.로그인_되어_있음("이순신");

        // when
        ExtractableResponse<Response> response = ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value()),
                () -> assertThat(response.jsonPath().getString("message")).isEqualTo("접급 권한이 없습니다."),
                () -> assertThat(response.jsonPath().getString("status")).isEqualTo("401"),
                () -> assertThat(response.jsonPath().getString("code")).isEqualTo("A003")
        );
    }

    @DisplayName("상품을 전체 조회한다.")
    @Test
    void getProducts() {
        // given
        ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token);
        ProductSteps.상품_등록(DEPOSIT_NAME, ProductType.DEPOSIT, token);

        // when
        ExtractableResponse<Response> response = ProductSteps.상품_전체_조회(token);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getList("name")).contains(LOAN_NAME, DEPOSIT_NAME),
                () -> assertThat(response.jsonPath().getList("type")).contains(ProductType.LOAN.name(), ProductType.DEPOSIT.name()),
                () -> assertThat(response.jsonPath().getList("state")).contains(ProductState.ACTIVITY.name(), ProductState.ACTIVITY.name())
        );
    }

    @DisplayName("상품을 단건 조회한다.")
    @Test
    void getProduct() {
        // given
        long productId = ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token).jsonPath().getLong("id");

        // when
        ExtractableResponse<Response> response = ProductSteps.상품_단건_조회(productId, token);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(LOAN_NAME),
                () -> assertThat(response.jsonPath().getString("type")).isEqualTo(ProductType.LOAN.name()),
                () -> assertThat(response.jsonPath().getString("state")).isEqualTo(ProductState.ACTIVITY.name())
        );
    }

    @DisplayName("상품 정보 수정한다.")
    @Test
    void update() {
        // given
        String unsecuredLoan = "신용대출";

        long productId = ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token).jsonPath().getLong("id");

        // when
        ProductSteps.상품_수정(productId, unsecuredLoan, token);
        ExtractableResponse<Response> response = ProductSteps.상품_단건_조회(productId, token);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(unsecuredLoan),
                () -> assertThat(response.jsonPath().getString("type")).isEqualTo(ProductType.LOAN.name()),
                () -> assertThat(response.jsonPath().getString("state")).isEqualTo(ProductState.ACTIVITY.name())
        );
    }

    @DisplayName("상품을 삭제한다.(상태 비활동으로 변경)")
    @Test
    void delete() {
        // given
        long productId = ProductSteps.상품_등록(LOAN_NAME, ProductType.LOAN, token).jsonPath().getLong("id");

        // when
        ExtractableResponse<Response> response = ProductSteps.상품_삭제(productId, token);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(LOAN_NAME),
                () -> assertThat(response.jsonPath().getString("type")).isEqualTo(ProductType.LOAN.name()),
                () -> assertThat(response.jsonPath().getString("state")).isEqualTo(ProductState.INACTIVITY.name())
        );
    }
}
