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
                .when().post("/api/v1/signup")
                .then().log().all().extract();
    }

    public static String 로그인_되어_있음(String name) {
        ExtractableResponse<Response> response = 로그인_요청(name);
        return response.jsonPath().getString("token");
    }

    public static ExtractableResponse<Response> 로그인_요청(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/api/v1/login")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 로그인_회원_조회(String token) {
        return given(token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/members")
                .then().log().all().extract();
    }
}
