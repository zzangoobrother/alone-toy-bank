package com.example.bankapi.product.applications;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankapi.product.applications.dto.response.ProductServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankproduct.applications.port.ProductCommandRepository;
import com.example.bankproduct.applications.port.ProductQueryRepository;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductCommandRepository productCommandRepository;
    private final ProductQueryRepository productQueryRepository;

    public ProductService(ProductCommandRepository productCommandRepository, ProductQueryRepository productQueryRepository) {
        this.productCommandRepository = productCommandRepository;
        this.productQueryRepository = productQueryRepository;
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

    public List<ProductServiceResponse> getProducts() {
        return productQueryRepository.findAll().stream()
                .map(product -> ProductServiceResponse.of(product))
                .toList();
    }

    public ProductServiceResponse getProduct(long productId) {
        return null;
    }
}
