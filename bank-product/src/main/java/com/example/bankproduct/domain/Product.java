package com.example.bankproduct.domain;

import com.example.bankcommon.domain.Name;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Product {
    private final Long id;
    private final ProductType type;
    private final Name name;
    private final ProductState state;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public Product(Long id, ProductType type, Name name, ProductState state, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("상품 타입은 필수값 입니다.");
        }

        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("상태는 필수값 입니다.");
        }

        this.id = id;
        this.type = type;
        this.name = name;
        this.state = state;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
