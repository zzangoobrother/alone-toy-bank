package com.example.bankapi.product.api.dto.response;

import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductResponse {
    private ProductType type;
    private String name;
    private ProductState state;

    @Builder
    public CreateProductResponse(ProductType type, String name, ProductState state) {
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static CreateProductResponse toResponse(CreateProductServiceResponse response) {
        return CreateProductResponse.builder()
                .type(response.getType())
                .name(response.getName())
                .state(response.getState())
                .build();
    }
}
