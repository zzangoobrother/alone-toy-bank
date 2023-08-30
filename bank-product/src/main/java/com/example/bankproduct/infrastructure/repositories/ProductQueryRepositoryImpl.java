package com.example.bankproduct.infrastructure.repositories;

import com.example.bankcommon.exception.EntityNotFoundException;
import com.example.bankcommon.exception.ErrorCode;
import com.example.bankproduct.applications.port.ProductQueryRepository;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.infrastructure.entities.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JpaProductRepository productRepository;

    public ProductQueryRepositoryImpl(JpaProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll().stream()
                .map(productEntity -> productEntity.toModel())
                .toList();
    }

    @Override
    public Product getById(long productId) {
        List<ProductEntity> all = productRepository.findAll();
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND)).toModel();
    }
}
