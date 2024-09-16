package org.factoriaf5.digital_academy.funko_shop.discount;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO {
    
    private Long id;
    private float percentage;

    private List<ProductDTO> products;
}
