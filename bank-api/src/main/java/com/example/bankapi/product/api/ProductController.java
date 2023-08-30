package com.example.bankapi.product.api;

import com.example.bankapi.product.api.dto.request.CreateProductRequest;
import com.example.bankapi.product.api.dto.request.UpdateProductRequest;
import com.example.bankapi.product.api.dto.response.CreateProductResponse;
import com.example.bankapi.product.api.dto.response.ProductResponse;
import com.example.bankapi.product.applications.ProductService;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankapi.product.applications.dto.response.ProductServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/api/v1/products")
    public List<ProductResponse> getProducts() {
        List<ProductServiceResponse> response = productService.getProducts();
        return response.stream()
                .map(ProductResponse::toResponse)
                .toList();
    }

    @GetMapping("/api/v1/products/{productId}")
    public ProductResponse getProducts(@PathVariable Long productId) {
        ProductServiceResponse response = productService.getProduct(productId);
        return ProductResponse.toResponse(response);
    }

    @PutMapping("/api/v1/products/{productId}")
    public ProductResponse update(@PathVariable Long productId, @Valid @RequestBody UpdateProductRequest request) {
        ProductServiceResponse response = productService.update(productId, request.getName());
        return ProductResponse.toResponse(response);
    }
}
