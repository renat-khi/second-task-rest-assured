package shop.api.positive;

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

public class ProductApiTests extends BaseApiTest {

    @Test
    public void checkThatProductListContainsProduct() {
        Product productExistsInProductList = createNewProduct();
        Response response = ProductRequestActions.getProductList();
        List<Product> productList = ProductResponseActions.getProductList(response);
        boolean isContains = productList.contains(productExistsInProductList);
        MainResponseActions.shouldHaveStatus(response, 200);
        Assert.assertTrue(isContains, "getting product list does not contain a product that exists with the product list");
    }

    @Test
    public void checkThatNewProductHasBeenAdded() {
        Product product = new Product("Phone", "Electronics", 10, 2);
        Response response = ProductRequestActions.addNewProduct(product);
        MainResponseActions.shouldHaveStatus(response, 201);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product added successfully", textMassage, "text does not match");
    }

    /**
     * По спецификации запрос должен возвращать Product, по факту List<Product> из одного элемента.
     */
    @Test
    public void checkThatReturnedInfoAboutProductById() {
        Product productExistsInProductList = createNewProduct();
        int id = 1;
        Response response = ProductRequestActions.getProductById(id);
        MainResponseActions.shouldHaveStatus(response, 200);
        Product productFromResponse = ProductResponseActions.getProduct(response);
        Assert.assertEquals(productFromResponse, productExistsInProductList, "products do not match");
    }

    @Test
    public void checkThatInfoAboutProductUpdatedById() {
        Product productForUpdateInfo = new Product(1, "Keyboard", "Electronics", 5, 1);
        Response response = ProductRequestActions.updateProductById(productForUpdateInfo);
        MainResponseActions.shouldHaveStatus(response, 200);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product updated successfully", textMassage, "text does not match");
    }

    @Test
    public void checkThatProductDeletedById() {
        int id = 1;
        Response response = ProductRequestActions.deleteProductById(id);
        MainResponseActions.shouldHaveStatus(response, 200);
        String textMassage = ResponseMessage.getTextMessageFromResponse(response);
        Assert.assertEquals("Product deleted successfully", textMassage, "text does not match");
    }

    private Product createNewProduct() {
        return new Product(1, "HP Pavilion Laptop", "Electronics", 10.99, 10.0);
    }

}