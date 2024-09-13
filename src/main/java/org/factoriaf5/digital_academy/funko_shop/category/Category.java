package org.factoriaf5.digital_academy.funko_shop.category;

import org.factoriaf5.digital_academy.funko_shop.product.Product;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;
    private Optional<String> imageHash;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}
