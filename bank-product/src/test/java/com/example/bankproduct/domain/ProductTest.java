package com.example.bankproduct.domain;

import com.example.bankcommon.domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductTest {

    @DisplayName("상품 타입 null 입력 에러")
    @Test
    void nullType() {
        assertThatThrownBy(() -> Product.builder()
                .name(Name.newInstance("대출"))
                .type(null)
                .state(ProductState.ACTIVITY)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 상태 null 입력 에러")
    @Test
    void nullState() {
        assertThatThrownBy(() -> Product.builder()
                .name(Name.newInstance("대출"))
                .type(ProductType.LOAN)
                .state(null)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품명 수정")
    @Test
    void upateName() {
        Product product = Product.builder()
                .name(Name.newInstance("대출"))
                .type(ProductType.LOAN)
                .state(ProductState.ACTIVITY)
                .build();

        Product updateProduct = product.update("신용대출");

        assertAll(
                () -> assertThat(updateProduct.getName().getName()).isEqualTo("신용대출"),
                () -> assertThat(updateProduct.getType()).isEqualTo(product.getType())
        );
    }

    @DisplayName("상품 상태 검증")
    @Test
    void isState() {
        Product product = Product.builder()
                .name(Name.newInstance("대출"))
                .type(ProductType.LOAN)
                .state(ProductState.ACTIVITY)
                .build();

        assertAll(
                () -> assertThat(product.isActivity()).isTrue(),
                () -> assertThat(product.isInactivity()).isFalse()
        );
    }
}