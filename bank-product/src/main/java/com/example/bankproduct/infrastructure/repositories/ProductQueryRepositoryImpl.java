package com.example.bankproduct.infrastructure.repositories;

import com.example.bankproduct.applications.port.ProductQueryRepository;
import com.example.bankproduct.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {
    @Override
    public List<Product> findAll() {
        return null;
    }
}
