package shop.service.user;

import io.restassured.response.Response;
import shop.model.UserLoginRequest;

import static io.restassured.RestAssured.given;

public interface UserRequestActions {

    static Response registration(UserLoginRequest userLoginRequest) {
        return given()
                .body(userLoginRequest)
                .when()
                .post("/register");
    }

    static Response login(UserLoginRequest userLoginRequest) {
        return given()
                .body(userLoginRequest)
                .when()
                .post("/login");
    }

    static String getToken(UserLoginRequest userLoginRequest) {
        return given()
                .body(userLoginRequest)
                .when()
                .post("/login")
                .body()
                .jsonPath()
                .getString("access_token");
    }

}