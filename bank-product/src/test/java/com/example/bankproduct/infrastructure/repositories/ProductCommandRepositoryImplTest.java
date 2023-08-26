package com.example.bankproduct.infrastructure.repositories;

import com.example.bankcommon.domain.Name;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import com.example.bankproduct.infrastructure.entities.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductCommandRepositoryImplTest {

    @Mock
    private JpaProductRepository productRepository;

    @InjectMocks
    private ProductCommandRepositoryImpl productCommandRepository;

    @DisplayName("상품 저장")
    @Test
    void create() {
        ProductEntity entity = ProductEntity.builder()
                .type(ProductType.LOAN)
                .name(Name.newInstance("대출"))
                .state(ProductState.ACTIVITY)
                .build();

        Product product = entity.toModel();

        when(productRepository.save(any())).thenReturn(entity);

        Product saveProduct = productCommandRepository.save(product);

        assertThat(saveProduct.getType()).isEqualTo(product.getType());
        assertThat(saveProduct.getName()).isEqualTo(product.getName());
        assertThat(saveProduct.getState()).isEqualTo(product.getState());
    }
}