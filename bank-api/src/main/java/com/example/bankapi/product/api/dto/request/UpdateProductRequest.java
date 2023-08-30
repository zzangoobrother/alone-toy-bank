package com.example.bankapi.product.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateProductRequest {
    @NotBlank(message = "상품명은 필수값 입니다.")
    private String name;

    public UpdateProductRequest(String name) {
        this.name = name;
    }

    public UpdateProductRequest toServiceRequest() {
        return new UpdateProductRequest(name);
    }
}
