package com.example.bankapi.product.acceptance;

import com.example.bankapi.AcceptanceTestSteps;
import com.example.bankproduct.domain.ProductType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ProductSteps extends AcceptanceTestSteps {

    public static ExtractableResponse<Response> 상품_등록(String name, ProductType type, String token) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("type", type.name());

        return given(token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/api/v1/products")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 상품_전체_조회(String token) {
        return given(token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/products")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 상품_단건_조회(Long id, String token) {
        return given(token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/products/{id}", id)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 상품_수정(Long productId, String name, String token) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        return given(token)
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/api/v1/products/{productId}", productId)
                .then().log().all().extract();
    }
}
