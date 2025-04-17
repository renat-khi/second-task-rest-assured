package shop.api.negative;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import shop.api.BaseApiTest;
import shop.model.Product;
import shop.service.ResponseMessage;
import shop.service.product.ProductRequestActions;
import shop.service.MainResponseActions;
import shop.service.product.ProductResponseActions;

import java.util.List;

public class ProductNegativeApiTests extends BaseApiTest {

    /**
     * Проверяем, что в продуктовом списке нет продукта не добавленного в список.
     */
    @Test
    public void checkThatProductListNotContainsProduct() {
        Product productNotExistsInProductList = new Product(99999, "999", "999", 999, 999);
        Response response = ProductRequestActions.getProductList();
        List<Product> productList = ProductResponseActions.getProductList(response);
        boolean isNotContains = productList.contains(productNotExistsInProductList);
        MainResponseActions.shouldHaveStatus(response, 200);
        Assert.assertFalse(isNotContains, "getting product list contain a product that not exists with the product list");
    }

    /**
     * Проверяем что при добавлении уже существующего продукта, получаем сообщение об ошибке.
     */
    @Test
    public void checkThatAddProductExistentInProductListGetErrorMessage() {
        Product product = new Product(1, "HP Pavilion Laptop", "Electronics", 10.99, 10.0);
        Response response = ProductRequestActions.addNewProduct(product);
        MainResponseActions.shouldHaveStatus(response, 404);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product already exists", textMassage, "text does not match");
    }

    /**
     * Проверяем что при получении информации о несуществующем продукте, получаем сообщение об ошибке.
     */
    @Test
    public void checkThatGetInfoAboutNonExistentProductGetErrorMessage() {
        int id = 100000;
        Response response = ProductRequestActions.getProductById(id);
        MainResponseActions.shouldHaveStatus(response, 404);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product not found", textMassage, "text does not match");
    }

    /**
     * Проверяем что при обновлении несуществующего продукта, получаем сообщение об ошибке.
     */
    @Test
    public void checkThatUpdateNonExistentProductGetErrorMessage() {
        Product productForUpdateInfo = new Product(99999, "HP Pavilion Laptop", "Electronics", 99999, 9999);
        Response response = ProductRequestActions.updateProductById(productForUpdateInfo);
        MainResponseActions.shouldHaveStatus(response, 404);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product not found", textMassage, "text does not match");
    }

    /**
     * Проверяем что при удалении несуществующего продукта, получаем сообщение об ошибке.
     */
    @Test
    public void checkThatDeleteNonExistentProductGetErrorMessage() {
        int id = 99999;
        Response response = ProductRequestActions.deleteProductById(id);
        MainResponseActions.shouldHaveStatus(response, 404);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product not found", textMassage, "text does not match");
    }

}