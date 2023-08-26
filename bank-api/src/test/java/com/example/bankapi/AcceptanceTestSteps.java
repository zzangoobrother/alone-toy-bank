package com.example.bankapi;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AcceptanceTestSteps {

    protected static RequestSpecification given() {
        return RestAssured
                .given().log().all();
    }

    protected static RequestSpecification given(String token) {
        return RestAssured
                .given().log().all()
                .auth().oauth2(token);
    }
}
