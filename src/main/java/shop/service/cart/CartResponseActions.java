package shop.service.cart;

import io.restassured.response.Response;
import shop.model.ShoppingCartResponse;

public interface CartResponseActions {

    static ShoppingCartResponse getCart(Response response) {
        return response.as(ShoppingCartResponse.class);
    }

}