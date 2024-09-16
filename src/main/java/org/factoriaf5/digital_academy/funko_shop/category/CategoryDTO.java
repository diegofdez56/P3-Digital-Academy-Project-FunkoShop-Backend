package org.factoriaf5.digital_academy.funko_shop.category;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String imageHash;
}
