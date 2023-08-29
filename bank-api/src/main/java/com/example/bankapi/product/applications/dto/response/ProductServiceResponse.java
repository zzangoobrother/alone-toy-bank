package com.example.bankapi.product.applications.dto.response;

import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductServiceResponse {
    private Long id;
    private ProductType type;
    private String name;
    private ProductState state;

    @Builder
    public ProductServiceResponse(Long id, ProductType type, String name, ProductState state) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static ProductServiceResponse of(Product product) {
        return ProductServiceResponse.builder()
                .id(product.getId())
                .type(product.getType())
                .name(product.getName().getName())
                .state(product.getState())
                .build();
    }
}
