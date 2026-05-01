package com.api.tests;


import org.testng.annotations.Test;

import com.api.endpoints.ClientEndpoint;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
public class EndpointTest {
    ClientEndpoint client = new ClientEndpoint();

    
   // @Test

    @Test(description = "Verify login")   

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
        System.out.println(response.getBody().asPrettyString());
        System.out.println(" Device Name: " + response.jsonPath().getString("name"));
        Allure.step("Test Result: " + response.getStatusCode());
        Allure.step("Item Name: " + response.jsonPath().getString("name"));
        Allure.step("Status Code: " + response.getStatusCode());
        Allure.addAttachment("Response Body", "application/json", response.asPrettyString());
        Allure.step("Name: " + response.jsonPath().getString("name"));

       // System.out.println("Name of the Item; " + response.jsonPath().getList("name").toString());

    }

}
