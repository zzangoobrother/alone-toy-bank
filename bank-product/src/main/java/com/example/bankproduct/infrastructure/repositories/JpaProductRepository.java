package com.example.bankproduct.infrastructure.repositories;

import com.example.bankproduct.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
