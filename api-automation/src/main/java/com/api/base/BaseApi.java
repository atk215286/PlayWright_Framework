package com.api.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecification request() {
        return RestAssured
                .given()
                .baseUri("https://api.restful-api.dev")
                //.baseUri(System.getProperty("api.base.url"))
                .contentType(ContentType.JSON)
                .log().all();
    }
}