package model;

import lombok.Data;

@Data
public class Product {

    public Product(String name, String category, double price, double discount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
    }

    private String name;
    private String category;
    private double price;
    private double discount;

}