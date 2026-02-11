package com.api.base;

import io.restassured.RestAssured;

public class TokenManager {

    private static String token;

    public static String getToken() {
        if (token == null) {
            token = RestAssured
                    .given()
                    .baseUri(System.getProperty("auth.url"))
                    .body("{\"username\":\"admin\",\"password\":\"admin\"}")
                    .post("/login")
                    .jsonPath()
                    .getString("token");
        }
        return token;
    }
}