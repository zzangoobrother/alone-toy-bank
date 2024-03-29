package com.example.bankapi.product.applications.dto.response;

import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductServiceResponse {
    private long id;
    private ProductType type;
    private String name;
    private ProductState state;

    @Builder
    public CreateProductServiceResponse(long id, ProductType type, String name, ProductState state) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static CreateProductServiceResponse of(Product product) {
        return CreateProductServiceResponse.builder()
                .id(product.getId())
                .type(product.getType())
                .name(product.getName().getName())
                .state(product.getState())
                .build();
    }
}
