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
}
