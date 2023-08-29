package com.example.bankproduct.applications.port;

import com.example.bankproduct.domain.Product;

import java.util.List;

public interface ProductQueryRepository {
    List<Product> findAll();
}
