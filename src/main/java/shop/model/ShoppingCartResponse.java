package shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponse {

    List<Product> cart;
    double total_price;
    double total_discount;

}