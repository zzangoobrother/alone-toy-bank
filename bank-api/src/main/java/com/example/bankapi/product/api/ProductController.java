package com.example.bankapi.product.api;

import com.example.bankapi.product.api.dto.request.CreateProductRequest;
import com.example.bankapi.product.api.dto.response.CreateProductResponse;
import com.example.bankapi.product.applications.ProductService;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/products")
    public CreateProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        CreateProductServiceResponse response = productService.create(request.toServiceRequest());
        return CreateProductResponse.toResponse(response);
    }
}
