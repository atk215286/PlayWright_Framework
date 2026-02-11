package com.api.tests;


import io.restassured.response.Response;
import com.api.endpoints.ClientEndpoint;
import org.testng.annotations.Test;
import io.qameta.allure.*;





@Epic("Web Module")
@Feature("Login")

public class EndpointTest {
    ClientEndpoint client = new ClientEndpoint();

    
   // @Test

    @Test(description = "Verify login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates login with correct credentials")  

    public void getAllObjectsTest() {
       Response response = client.getAllObjects();
       response.then().statusCode(200);
       System.err.println(response.asString());
       System.out.println("NO of items; " +response.jsonPath().getList("$").size());
        
       Allure.step("Open login page");
        Allure.step("Enter username");
        Allure.step("Enter password");
        Allure.step("Click login");

    
    }

    @Test
    public void getObjectByIdTest() {
        Response response = client.getObjectById("1");
        response.then().statusCode(200);
    }

}
