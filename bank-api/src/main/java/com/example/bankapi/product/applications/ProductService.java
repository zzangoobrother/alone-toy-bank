package com.example.bankapi.product.applications;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankproduct.applications.port.ProductCommandRepository;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductCommandRepository productCommandRepository;

    public ProductService(ProductCommandRepository productCommandRepository) {
        this.productCommandRepository = productCommandRepository;
    }

    public CreateProductServiceResponse create(CreateProductServiceRequest request) {
        Product product = Product.builder()
                .type(request.getType())
                .name(Name.newInstance(request.getName()))
                .state(ProductState.ACTIVITY)
                .build();

        Product saveProduct = productCommandRepository.save(product);
        return CreateProductServiceResponse.of(saveProduct);
    }
}
