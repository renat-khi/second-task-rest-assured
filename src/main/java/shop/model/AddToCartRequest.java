package shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddToCartRequest {

    private double product_id;
    private double quantity;

}