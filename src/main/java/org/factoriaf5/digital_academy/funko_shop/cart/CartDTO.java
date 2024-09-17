package org.factoriaf5.digital_academy.funko_shop.cart;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.cart_item.CartItemDTO;
import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    
    private Long id;
    private UserDTO user;
    private List<CartItemDTO> cartItems;
    private float totalPrice;
}
