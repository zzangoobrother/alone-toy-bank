package com.example.bankproduct.infrastructure.repositories;

import com.example.bankproduct.applications.port.ProductCommandRepository;
import com.example.bankproduct.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCommandRepositoryImpl implements ProductCommandRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductCommandRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {

        return null;
    }
}
