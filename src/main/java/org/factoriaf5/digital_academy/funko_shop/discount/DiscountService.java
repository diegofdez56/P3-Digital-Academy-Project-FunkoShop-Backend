package org.factoriaf5.digital_academy.funko_shop.discount;

import org.factoriaf5.digital_academy.funko_shop.product.Product;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public float applyDiscount(Product product) {
        if (product.getDiscount() != null && product.getDiscount().isActive()) {
            float discountPercentage = product.getDiscount().getPercentage();
            return product.getPrice() * (1 - discountPercentage / 100);
        }
        return product.getPrice();
    }
}
