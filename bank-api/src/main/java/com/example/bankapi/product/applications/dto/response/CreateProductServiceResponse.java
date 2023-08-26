package com.example.bankapi.product.applications.dto.response;

import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductServiceResponse {
    private ProductType type;
    private String name;
    private ProductState state;

    @Builder
    public CreateProductServiceResponse(ProductType type, String name, ProductState state) {
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static CreateProductServiceResponse of(Product product) {
        return CreateProductServiceResponse.builder()
                .type(product.getType())
                .name(product.getName().getName())
                .state(product.getState())
                .build();
    }
}
