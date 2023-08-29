package com.example.bankapi.product.api.dto.response;

import com.example.bankapi.product.applications.dto.response.ProductServiceResponse;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {
    private ProductType type;
    private String name;
    private ProductState state;

    @Builder
    public ProductResponse(ProductType type, String name, ProductState state) {
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static ProductResponse toResponse(ProductServiceResponse response) {
        return ProductResponse.builder()
                .type(response.getType())
                .name(response.getName())
                .state(response.getState())
                .build();
    }
}
