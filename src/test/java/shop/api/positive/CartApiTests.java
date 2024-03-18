package shop.api.positive;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import shop.api.BaseApiTest;
import shop.model.AddToCartRequest;
import shop.model.Product;
import shop.model.ShoppingCartResponse;
import shop.model.UserLoginRequest;
import shop.service.ResponseMessage;
import shop.service.cart.CartRequestActions;
import shop.service.cart.CartResponseActions;
import shop.service.MainResponseActions;

import static shop.api.positive.UserLoginRequestApiTests.createNewUser;

public class CartApiTests extends BaseApiTest {

    @Test
    public void checkAddProductToCart() {
        UserLoginRequest userLoginRequest = createNewUser();
        AddToCartRequest addToCartRequest = new AddToCartRequest(1, 2);
        Response response = CartRequestActions.addProductToCart(addToCartRequest, userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 201);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product added to cart successfully", textMassage, "text does not match");
    }

    @Test
    public void checkNameFirstProductInUserCart() {
        UserLoginRequest userLoginRequest = createNewUser();
        Response response = CartRequestActions.getCart(userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 200);
        ShoppingCartResponse shoppingCartResponse = CartResponseActions.getCart(response);
        Product firstProductInCart = shoppingCartResponse.getCart().get(0);
        Assert.assertEquals("HP Pavilion Laptop", firstProductInCart.getName(), "name product does not match");
    }

    /**
     * По спецификации текст сообщения ожидается "Product removed from cart successfully", по факту "Product removed from cart"
     */
    @Test
    public void checkRemoveProductFromCartById() {
        UserLoginRequest userLoginRequest = createNewUser();
        int id = 1;
        Response response = CartRequestActions.removeProductFromCartById(id, userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 200);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product removed from cart successfully", textMassage, "text does not match");
    }

}