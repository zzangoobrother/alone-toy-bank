package com.example.bankapi.product.applications;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankproduct.applications.port.ProductCommandRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductCommandRepository productCommandRepository;

    public ProductService(ProductCommandRepository productCommandRepository) {
        this.productCommandRepository = productCommandRepository;
    }

    public CreateProductServiceResponse create(CreateProductServiceRequest request) {
        return null;
    }
}
