package shop.service;

import io.restassured.response.Response;

public interface ResponseMessage {

    static String getTextMessageFromResponse(Response response) {
        return response
                .body()
                .jsonPath()
                .getString("message");
    }

    static String getTextMsqFromResponse(Response response) {
        return response
                .body()
                .jsonPath()
                .getString("msg");
    }

}