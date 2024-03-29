package com.example.bankapi.product.applications;

import com.example.bankapi.product.applications.dto.request.CreateProductServiceRequest;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankapi.product.applications.dto.response.ProductServiceResponse;
import com.example.bankcommon.domain.Name;
import com.example.bankcommon.exception.NotUpdateEntityInactivityException;
import com.example.bankproduct.applications.port.FakeProductCommandRepository;
import com.example.bankproduct.applications.port.ProductCommandRepository;
import com.example.bankproduct.applications.port.ProductQueryRepository;
import com.example.bankproduct.domain.Product;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductServiceTest {

    private ProductCommandRepository productCommandRepository;

    private ProductQueryRepository productQueryRepository;

    private ProductService productService;

    @BeforeEach
    void setup() {
        FakeProductCommandRepository fakeProductCommandRepository = new FakeProductCommandRepository();

        productCommandRepository = fakeProductCommandRepository;
        productQueryRepository = fakeProductCommandRepository;

        productService = new ProductService(productCommandRepository, productQueryRepository);
    }

    @DisplayName("상품 등록")
    @Test
    void create() {
        CreateProductServiceRequest request = CreateProductServiceRequest.builder().name("대출").type(ProductType.LOAN).build();

        CreateProductServiceResponse response = productService.create(request);

        assertThat(response.getName()).isEqualTo(request.getName());
    }

    @DisplayName("상품 전체 조회")
    @Test
    void getProducts() {
        Product loanProduct = Product.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name(Name.newInstance("대출"))
                .state(ProductState.ACTIVITY)
                .build();
        Product depositProduct = Product.builder()
                .id(2L)
                .type(ProductType.DEPOSIT)
                .name(Name.newInstance("입출금"))
                .state(ProductState.ACTIVITY)
                .build();

        productCommandRepository.save(loanProduct);
        productCommandRepository.save(depositProduct);

        List<ProductServiceResponse> responses = productService.getProducts();

        assertThat(responses.get(0).getName()).isEqualTo("대출");
        assertThat(responses.get(0).getType()).isEqualTo(ProductType.LOAN);
        assertThat(responses.get(0).getState()).isEqualTo(ProductState.ACTIVITY);
        assertThat(responses.get(1).getName()).isEqualTo("입출금");
        assertThat(responses.get(1).getType()).isEqualTo(ProductType.DEPOSIT);
        assertThat(responses.get(1).getState()).isEqualTo(ProductState.ACTIVITY);
    }

    @DisplayName("상품 단건 조회")
    @Test
    void getProduct() {
        Product product = Product.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name(Name.newInstance("대출"))
                .state(ProductState.ACTIVITY)
                .build();

        productCommandRepository.save(product);

        ProductServiceResponse responses = productService.getProduct(1L);

        assertThat(responses.getName()).isEqualTo("대출");
        assertThat(responses.getType()).isEqualTo(ProductType.LOAN);
        assertThat(responses.getState()).isEqualTo(ProductState.ACTIVITY);
    }

    @DisplayName("상품 수정")
    @Test
    void update() {
        Product product = Product.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name(Name.newInstance("대출"))
                .state(ProductState.ACTIVITY)
                .build();

        productCommandRepository.save(product);

        ProductServiceResponse responses = productService.update(1L, "신용대출");

        assertThat(responses.getId()).isEqualTo(product.getId());
        assertThat(responses.getName()).isEqualTo("신용대출");
        assertThat(responses.getType()).isEqualTo(ProductType.LOAN);
        assertThat(responses.getState()).isEqualTo(ProductState.ACTIVITY);
    }

    @DisplayName("상품 수정 중 상품 상태가 비활동이라면 에러 발생")
    @Test
    void updateProductInactivity () {
        Product product = Product.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name(Name.newInstance("대출"))
                .state(ProductState.INACTIVITY)
                .build();

        productCommandRepository.save(product);

        assertThatThrownBy(() -> productService.update(1L, "신용대출")).isInstanceOf(NotUpdateEntityInactivityException.class);
    }

    @DisplayName("상품 삭제")
    @Test
    void delete() {
        Product product = Product.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name(Name.newInstance("대출"))
                .state(ProductState.ACTIVITY)
                .build();

        productCommandRepository.save(product);

        ProductServiceResponse responses = productService.delete(1L);

        assertThat(responses.getId()).isEqualTo(product.getId());
        assertThat(responses.getName()).isEqualTo("대출");
        assertThat(responses.getType()).isEqualTo(ProductType.LOAN);
        assertThat(responses.getState()).isEqualTo(ProductState.INACTIVITY);
    }
}