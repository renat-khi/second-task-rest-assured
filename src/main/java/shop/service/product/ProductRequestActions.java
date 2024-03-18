package shop.service.product;

import io.restassured.response.Response;
import shop.model.Product;

import static io.restassured.RestAssured.given;

public interface ProductRequestActions {

    static Response getProductList() {
        return given()
                .when()
                .get("/products");
    }

    static Response getProductById(int id) {
        return given()
                .when()
                .get("/products/" + id);
    }

    static Response updateProductById(Product product) {
        return given()
                .body(product)
                .when()
                .put("/products/" + product.getId());
    }

    static Response deleteProductById(int id) {
        return given()
                .when()
                .delete("/products/" + id);
    }

    static Response addNewProduct(Product product) {
        return given()
                .body(product)
                .when()
                .post("/products");
    }

}