package shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    public Product(String name, String category, double price, double discount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
    }

    public Product(int id, String name, String category, double price, double discount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
    }

    private int id;
    private String name;
    private String category;
    private double price;
    private double discount;
    private int quantity;

}