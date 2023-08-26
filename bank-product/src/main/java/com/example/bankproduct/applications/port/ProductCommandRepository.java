package com.example.bankproduct.applications.port;

import com.example.bankproduct.domain.Product;

public interface ProductCommandRepository {
    Product save(Product product);
}
