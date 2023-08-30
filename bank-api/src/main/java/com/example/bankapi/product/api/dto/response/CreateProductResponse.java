package com.example.bankapi.product.api.dto.response;

import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductResponse {
    private long id;
    private ProductType type;
    private String name;
    private ProductState state;

    @Builder
    public CreateProductResponse(long id, ProductType type, String name, ProductState state) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static CreateProductResponse toResponse(CreateProductServiceResponse response) {
        return CreateProductResponse.builder()
                .id(response.getId())
                .type(response.getType())
                .name(response.getName())
                .state(response.getState())
                .build();
    }
}
