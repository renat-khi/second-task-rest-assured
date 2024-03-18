package shop.service.cart;

import io.restassured.http.Header;
import io.restassured.response.Response;
import shop.model.AddToCartRequest;
import shop.model.Product;
import shop.model.UserLoginRequest;

import static io.restassured.RestAssured.given;
import static shop.service.user.UserRequestActions.getToken;

public interface CartRequestActions {

    static Response getCartWithoutAuthorization() {
        return given()
                .when()
                .get("/cart");
    }

    static Response getCart(UserLoginRequest userLoginRequest) {
        return given()
                .header(new Header("Authorization", "Bearer " + getToken(userLoginRequest)))
                .when()
                .get("/cart");
    }


    static Response addProductToCart(AddToCartRequest addToCartRequest, UserLoginRequest userLoginRequest) {
        return given()
                .body(addToCartRequest)
                .header(new Header("Authorization", "Bearer " + getToken(userLoginRequest)))
                .when()
                .post("/cart");
    }

    static Response addNewProduct(Product product) {
        return given()
                .body(product)
                .when()
                .post("/products");
    }

    static Response removeProductFromCartById(int id, UserLoginRequest userLoginRequest) {
        return given()
                .header(new Header("Authorization", "Bearer " + getToken(userLoginRequest)))
                .when()
                .delete("/cart/" + id);
    }

}