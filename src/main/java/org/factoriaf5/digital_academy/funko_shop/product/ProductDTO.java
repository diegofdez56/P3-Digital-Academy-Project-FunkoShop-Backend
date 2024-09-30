package org.factoriaf5.digital_academy.funko_shop.product;

import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String imageHash;
    private String description;
    @Positive(message = "Price must be positive")
    private float price;
    private float discountedPrice;
    @Positive(message = "Stock must be positive or zero")
    private int stock;
    private boolean isAvailable;
    private boolean isNew;
    private CategoryDTO category;
    private int discount;
}
