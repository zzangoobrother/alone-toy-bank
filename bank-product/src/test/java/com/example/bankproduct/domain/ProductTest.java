package com.example.bankproduct.domain;

import com.example.bankcommon.domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}