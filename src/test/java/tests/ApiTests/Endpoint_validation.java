package tests.ApiTests;

import org.testng.annotations.Test;

import com.playwright.core.RestApi.Endpoints.app_demo.ClientEndpoint;

import io.restassured.response.Response;

public class Endpoint_validation {
    ClientEndpoint client = new ClientEndpoint();

    @Test
    public void getAllObjectsTest() {
       Response response = client.getAllObjects();
       response.then().statusCode(200);
       System.err.println(response.asString());
       System.out.println("NO of items; " +response.jsonPath().getList("$").size());
    }

    @Test
    public void getObjectByIdTest() {
        Response response = client.getObjectById("1");
        response.then().statusCode(200);
    }

}
