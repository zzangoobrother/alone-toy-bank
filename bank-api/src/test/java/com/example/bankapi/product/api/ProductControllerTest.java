package com.example.bankapi.product.api;

import com.example.bankapi.product.api.dto.request.CreateProductRequest;
import com.example.bankapi.product.applications.ProductService;
import com.example.bankapi.product.applications.dto.response.CreateProductServiceResponse;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}