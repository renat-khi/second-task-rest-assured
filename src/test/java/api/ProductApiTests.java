package api;

import model.Product;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductApiTests extends BaseApiTest {

    /**
     * Получает список продуктов
     */
    @Test
    public void getProductList() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    /**
     * Добавляет новые продукты
     */
    @Test
    public void addNewProduct() {
        Product product = createNewProduct();
        given()
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(201);
    }

    /**
     * Получает информацию о продукте по id
     */
    @Test
    public void getProductById() {
        String id = "1";
        given()
                .when()
                .get("/products/" + id)
                .then()
                .statusCode(200);
    }

    /**
     * Обновляет информацию о продукте по id
     */
    @Test
    public void updateProductById() {
        String id = "1";
        Product product = createNewProduct();
        given()
                .body(product)
                .when()
                .put("/products/" + id)
                .then()
                .statusCode(200);
    }

    /**
     * Удаляет продукт по id
     */
    @Test
    public void deleteProductById() {
        String id = "1";
        given()
                .when()
                .delete("/products/" + id)
                .then()
                .statusCode(200);
    }

    private Product createNewProduct() {
        return new Product("Phone", "Electronics", 10, 2);
    }

}