package com.example.bankapi.product.applications;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankapi.product.applications.dto.response.ProductServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankcommon.exception.ErrorCode;
import com.example.bankcommon.exception.NotUpdateEntityInactivityException;
import com.example.bankproduct.applications.port.ProductCommandRepository;
import com.example.bankproduct.applications.port.ProductQueryRepository;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductCommandRepository productCommandRepository;
    private final ProductQueryRepository productQueryRepository;

    public ProductService(ProductCommandRepository productCommandRepository, ProductQueryRepository productQueryRepository) {
        this.productCommandRepository = productCommandRepository;
        this.productQueryRepository = productQueryRepository;
    }

    @Transactional
    public CreateProductServiceResponse create(CreateProductServiceRequest request) {
        Product product = Product.builder()
                .type(request.getType())
                .name(Name.newInstance(request.getName()))
                .state(ProductState.ACTIVITY)
                .build();

        Product saveProduct = productCommandRepository.save(product);
        return CreateProductServiceResponse.of(saveProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductServiceResponse> getProducts() {
        return productQueryRepository.getAll().stream()
                .map(product -> ProductServiceResponse.of(product))
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductServiceResponse getProduct(long productId) {
        return ProductServiceResponse.of(productQueryRepository.getById(productId, ProductState.ACTIVITY));
    }

    @Transactional
    public ProductServiceResponse update(long productId, String name) {
        Product product = productQueryRepository.getById(productId);

        if (product.isInactivity()) {
            throw new NotUpdateEntityInactivityException(ErrorCode.UPDATE_NOT_STATUS);
        }

        product = product.update(name);
        product = productCommandRepository.save(product);

        return ProductServiceResponse.of(product);
    }
}
