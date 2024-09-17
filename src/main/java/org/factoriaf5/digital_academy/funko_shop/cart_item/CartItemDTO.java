package org.factoriaf5.digital_academy.funko_shop.cart_item;

import org.factoriaf5.digital_academy.funko_shop.cart.CartDTO;
import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private CartDTO cart;
    private ProductDTO product;
    private int quantity;
    private float price;
}
