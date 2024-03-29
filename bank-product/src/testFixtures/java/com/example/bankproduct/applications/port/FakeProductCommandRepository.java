package com.example.bankproduct.applications.port;

import com.example.bankcommon.domain.Name;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class FakeProductCommandRepository implements ProductCommandRepository, ProductQueryRepository {
    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Product> products = new ArrayList<>();

    @Override
    public Product save(Product product) {
        if (product.getId() == null || product.getId() == 0) {
            Product newMember = Product.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .type(product.getType())
                    .name(Name.newInstance(product.getName().getName()))
                    .state(product.getState())
                    .createdAt(product.getCreatedAt())
                    .modifiedAt(product.getModifiedAt())
                    .build();

            products.add(newMember);
            return newMember;
        }

        products.removeIf(m -> Objects.equals(m.getId(), product.getId()));
        products.add(product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(long productId, ProductState state) {
        return products.stream()
                .filter(product -> product.getId() == productId && product.getState() == state)
                .findAny().get();
    }

    @Override
    public Product getById(long productId) {
        return products.stream()
                .filter(product -> product.getId() == productId)
                .findAny().get();
    }
}
