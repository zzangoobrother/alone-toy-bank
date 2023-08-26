package com.example.bankapi.product.applications.dto.request;

import com.example.bankproduct.domain.ProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductServiceRequest {

    private String name;
    private ProductType type;

    @Builder
    public CreateProductServiceRequest(String name, ProductType type) {
        this.name = name;
        this.type = type;
    }
}
