package shop.api.negative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import shop.api.BaseApiTest;
import shop.model.AddToCartRequest;
import shop.model.UserLoginRequest;
import shop.service.ResponseMessage;
import shop.service.cart.CartRequestActions;
import shop.service.MainResponseActions;

import static shop.api.positive.UserLoginRequestApiTests.createNewUser;

public class CartApiNegativeTest extends BaseApiTest {
    @Test
    public void checkingCartWithoutAuthorization() {
        Response response = CartRequestActions.getCartWithoutAuthorization();
        MainResponseActions.shouldHaveStatus(response, 401);
        String textMsq = ResponseMessage.getTextMsqFromResponse(response);
        Assert.assertEquals(textMsq, "Missing Authorization Header", "Text does not match");
    }

    @Test
    public void checkingAdditionNonExistentProductToCart() {
        UserLoginRequest userLoginRequest = createNewUser();
        AddToCartRequest nonExistentProduct = new AddToCartRequest(100000, 200);
        Response response = CartRequestActions.addProductToCart(nonExistentProduct, userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 404);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product not found", textMassage, "Text does not match");
    }

    /**
     * По спецификации текст об ошибке "Product not found", по факту "Product not found in cart"
     */
    @Test
    public void checkingDeletionNonExistentProductFromCart() {
        UserLoginRequest userLoginRequest = createNewUser();
        int idNonExistentProductInCart = 100000;
        Response response = CartRequestActions.removeProductFromCartById(idNonExistentProductInCart, userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 404);
        String textErrorMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product not found", textErrorMassage, "Text does not match");
    }

}