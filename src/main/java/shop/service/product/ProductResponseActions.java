package shop.service.product;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import shop.model.Product;

import java.util.List;

public interface ProductResponseActions {

    static List<Product> getProductList(Response response) {
        return response.as(new TypeRef<>() {
        });
    }

    static Product getProduct(Response response) {
        return response.as(Product.class);
    }

}