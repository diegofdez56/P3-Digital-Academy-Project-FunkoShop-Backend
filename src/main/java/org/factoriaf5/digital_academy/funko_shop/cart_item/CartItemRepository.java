package org.factoriaf5.digital_academy.funko_shop.cart_item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
}
