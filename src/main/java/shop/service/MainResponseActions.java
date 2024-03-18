package shop.service;

import io.restassured.response.Response;

public interface MainResponseActions {

    static void shouldHaveStatus(Response response, int statusCode) {
        response
                .then()
                .statusCode(statusCode);
    }

}