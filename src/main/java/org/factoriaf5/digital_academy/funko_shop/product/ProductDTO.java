package org.factoriaf5.digital_academy.funko_shop.product;

import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    
    private Long id;
    private String name;
    private String imageHash;
    private String description;
    private float price;
    private int stock;
    private boolean isAvailable;
    private CategoryDTO category;
    private DiscountDTO discount;
}
