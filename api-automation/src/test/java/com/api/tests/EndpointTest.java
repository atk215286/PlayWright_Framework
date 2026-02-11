package com.api.tests;


import io.restassured.response.Response;
import com.api.endpoints.ClientEndpoint;
import org.testng.annotations.Test;




public class EndpointTest {
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
