package api;

import io.restassured.http.Header;
import model.AddProductToCart;
import org.testng.annotations.Test;

import static api.UserApiTests.getToken;
import static io.restassured.RestAssured.given;

public class CartApiTests extends BaseApiTest {

    /**
     * Получает корзину пользователя с товарами
     */
    @Test
    public void getUsersCartWithProducts() {
        given()
                .header(new Header("Authorization", "Bearer " + getToken()))
                .when()
                .get("/cart")
                .then()
                .statusCode(200);
    }

    /**
     * Добавляет товар в корзину
     */
    @Test
    public void addProductToCart() {
        AddProductToCart addProductToCart = createAddProductToCart();
        given()
                .body(addProductToCart)
                .header(new Header("Authorization", "Bearer " + getToken()))
                .when()
                .post("/cart")
                .then()
                .statusCode(201);
    }

    /**
     * Удаляет товар из корзины
     */
    @Test
    public void removeProductFromCartById() {
        String id = "1";
        given()
                .header(new Header("Authorization", "Bearer " + getToken()))
                .when()
                .delete("/cart/" + id)
                .then()
                .statusCode(200);
    }

    private AddProductToCart createAddProductToCart() {
        return new AddProductToCart(1, 2);
    }

}