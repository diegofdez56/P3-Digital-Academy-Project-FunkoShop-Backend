package org.factoriaf5.digital_academy.funko_shop.favorite;

import lombok.*;

import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteDTO {
    
    private Long id;
    private ProductDTO product;
    private Long user;
}
