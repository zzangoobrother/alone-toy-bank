package com.example.bankapi.product.applications;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankproduct.applications.port.FakeProductCommandRepository;
import com.example.bankproduct.applications.port.ProductCommandRepository;
import com.example.bankproduct.domain.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private ProductCommandRepository productCommandRepository;

    private ProductService productService;

    @BeforeEach
    void setup() {
        productCommandRepository = new FakeProductCommandRepository();
        productService = new ProductService(productCommandRepository);
    }

    @DisplayName("상품 등록")
    @Test
    void create() {
        CreateProductServiceRequest request = CreateProductServiceRequest.builder().name("대출").type(ProductType.LOAN).build();

        CreateProductServiceResponse response = productService.create(request);

        assertThat(response.getName()).isEqualTo(request.getName());
    }
}