package com.example.bankproduct.infrastructure.entities;

import com.example.bankcommon.domain.BaseEntity;
import com.example.bankcommon.domain.Name;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType type;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ProductState state;

    @Builder
    public ProductEntity(ProductType type, Name name, ProductState state) {
        this.type = type;
        this.name = name;
        this.state = state;
    }

    public static ProductEntity toEntity(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
                .type(product.getType())
                .name(product.getName())
                .state(product.getState())
                .build();

        productEntity.id = product.getId();
        productEntity.createdAt = product.getCreatedAt();
        productEntity.modifiedAt = product.getModifiedAt();

        return productEntity;
    }

    public Product toModel() {
        return Product.builder()
                .id(getId())
                .type(getType())
                .name(getName())
                .state(getState())
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }
}
