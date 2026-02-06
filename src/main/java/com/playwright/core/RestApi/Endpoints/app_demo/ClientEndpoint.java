package com.playwright.core.RestApi.Endpoints.app_demo;
import java.util.Map;

import com.playwright.core.RestApi.BaseApi;

import io.restassured.response.Response;

public class ClientEndpoint extends BaseApi {

    // GET /objects
    public Response getAllObjects() {
        return request()
                .get("/objects");
    }

    // GET /objects/{id}
    public Response getObjectById(String id) {
        return request()
                .get("/objects/" + id);
    }

    // POST /objects
    public Response createObject(Map<String, Object> payload) {
        return request()
                .body(payload)
                .post("/objects");
    }

    // PUT /objects/{id}
    public Response updateObject(String id, Map<String, Object> payload) {
        return request()
                .body(payload)
                .put("/objects/" + id);
    }

    // PATCH /objects/{id}
    public Response patchObject(String id, Map<String, Object> payload) {
        return request()
                .body(payload)
                .patch("/objects/" + id);
    }

    // DELETE /objects/{id}
    public Response deleteObject(String id) {
        return request()
                .delete("/objects/" + id);
    }
}