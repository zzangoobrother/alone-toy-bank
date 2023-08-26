package com.example.bankapi.product.api.dto.request;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankproduct.domain.ProductType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductRequest {
    @NotBlank(message = "상품명은 필수값 입니다.")
    private String name;

    @NotBlank(message = "상품 타입은 필수값 입니다.")
    private ProductType type;

    public CreateProductRequest(String name, ProductType type) {
        this.name = name;
        this.type = type;
    }

    public CreateProductServiceRequest toServiceRequest() {
        return new CreateProductServiceRequest(name, type);
    }
}
