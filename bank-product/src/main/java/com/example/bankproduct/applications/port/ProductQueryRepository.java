package com.example.bankproduct.applications.port;

import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;

import java.util.List;

public interface ProductQueryRepository {
    List<Product> getAll();

    Product getById(long productId, ProductState state);

    Product getById(long productId);
}
