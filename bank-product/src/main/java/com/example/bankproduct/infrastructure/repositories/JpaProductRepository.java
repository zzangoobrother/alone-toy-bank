package com.example.bankproduct.infrastructure.repositories;

import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByIdAndState(Long poroductId, ProductState state);
}
