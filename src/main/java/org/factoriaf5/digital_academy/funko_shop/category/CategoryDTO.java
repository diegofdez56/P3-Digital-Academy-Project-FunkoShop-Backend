package org.factoriaf5.digital_academy.funko_shop.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String imageHash;

    public CategoryDTO(Long id) {
        this.id = id;
    }
    
}
