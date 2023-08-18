package com.example.bankapi.member.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MemberSteps extends AcceptanceTestSteps {

    public static ExtractableResponse<Response> 회원_가입_요청(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/api/signup")
                .then().log().all().extract();
    }
}
