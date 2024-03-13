package model;

import lombok.Data;

@Data
public class AddProductToCart {

    public AddProductToCart(double product_id, double quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    private double product_id;
    private double quantity;

}