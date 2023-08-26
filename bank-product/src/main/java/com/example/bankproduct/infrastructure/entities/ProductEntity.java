package com.example.bankproduct.infrastructure.entities;

import com.example.bankcommon.domain.BaseEntity;
import com.example.bankcommon.domain.Name;
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
}
