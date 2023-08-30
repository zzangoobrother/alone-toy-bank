package com.example.bankapi.product.api;

import com.example.bankapi.product.api.dto.request.CreateProductRequest;
import com.example.bankapi.product.api.dto.request.UpdateProductRequest;
import com.example.bankapi.product.applications.ProductService;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
import com.example.bankapi.product.applications.dto.response.ProductServiceResponse;
import com.example.bankproduct.domain.ProductState;
import com.example.bankproduct.domain.ProductType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @DisplayName("상품 등록 성공")
    @Test
    void create() throws Exception {
        // given
        CreateProductRequest request = new CreateProductRequest("대출", ProductType.LOAN);
        CreateProductServiceResponse response = CreateProductServiceResponse.builder()
                .type(ProductType.LOAN)
                .name("대출")
                .state(ProductState.ACTIVITY)
                .build();

        when(productService.create(any())).thenReturn(response);

        mockMvc.perform(
                        post("/api/v1/products")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value(response.getType().name()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.state").value(response.getState().name()));
    }

    @DisplayName("상품명이 null 이면 에러")
    @Test
    void nullName() throws Exception {
        CreateProductRequest request = new CreateProductRequest(null, ProductType.LOAN);

        mockMvc.perform(
                        post("/api/v1/products")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("상품명이 빈값이면 에러")
    @Test
    void notInputName() throws Exception {
        CreateProductRequest request = new CreateProductRequest("", ProductType.LOAN);

        mockMvc.perform(
                        post("/api/v1/products")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("상품 타입이 null 이면 에러")
    @Test
    void nullType() throws Exception {
        CreateProductRequest request = new CreateProductRequest("대출", null);

        mockMvc.perform(
                        post("/api/v1/products")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("상품 전체 조회")
    @Test
    void getProducts() throws Exception {
        ProductServiceResponse response1 = ProductServiceResponse.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name("대출")
                .state(ProductState.ACTIVITY)
                .build();
        ProductServiceResponse response2 = ProductServiceResponse.builder()
                .id(2L)
                .type(ProductType.DEPOSIT)
                .name("입출금")
                .state(ProductState.ACTIVITY)
                .build();

        List<ProductServiceResponse> responses = List.of(response1, response2);
        when(productService.getProducts()).thenReturn(responses);

        mockMvc.perform(
                        get("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value(response1.getType().name()))
                .andExpect(jsonPath("$[0].name").value(response1.getName()))
                .andExpect(jsonPath("$[0].state").value(response1.getState().name()))
                .andExpect(jsonPath("$[1].type").value(response2.getType().name()))
                .andExpect(jsonPath("$[1].name").value(response2.getName()))
                .andExpect(jsonPath("$[1].state").value(response2.getState().name()));
    }

    @DisplayName("상품 단건 조회")
    @Test
    void getProduct() throws Exception {
        ProductServiceResponse response = ProductServiceResponse.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name("대출")
                .state(ProductState.ACTIVITY)
                .build();

        when(productService.getProduct(anyLong())).thenReturn(response);

        mockMvc.perform(
                        get("/api/v1/products/{productId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(response.getType().name()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.state").value(response.getState().name()));
    }

    @DisplayName("상품 수정")
    @Test
    void update() throws Exception {
        UpdateProductRequest request = new UpdateProductRequest("신용대출");
        ProductServiceResponse response = ProductServiceResponse.builder()
                .id(1L)
                .type(ProductType.LOAN)
                .name("신용대출")
                .state(ProductState.ACTIVITY)
                .build();

        when(productService.update(anyLong(), anyString())).thenReturn(response);

        mockMvc.perform(
                        put("/api/v1/products/{productId}", 1)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(response.getType().name()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.state").value(response.getState().name()));
    }

    @DisplayName("상품 수정 상품명이 null 이면 에러")
    @Test
    void updateNotNull() throws Exception {
        UpdateProductRequest request = new UpdateProductRequest(null);

        mockMvc.perform(
                        put("/api/v1/products/{productId}", 1)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("유효한 입력값이 아닙니다."))
                .andExpect(jsonPath("$.errors.[0].reason").value("상품명은 필수값 입니다."));
    }

    @DisplayName("상품 수정 상품명이 빈값이면 에러")
    @Test
    void updateNotEmpty() throws Exception {
        UpdateProductRequest request = new UpdateProductRequest("");

        mockMvc.perform(
                        put("/api/v1/products/{productId}", 1)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("유효한 입력값이 아닙니다."))
                .andExpect(jsonPath("$.errors.[0].reason").value("상품명은 필수값 입니다."));
    }
}