package org.factoriaf5.digital_academy.funko_shop.discount;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.product.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discounts")
public class Discount {
    
    @Id
    @GeneratedValue
    @Column(name = "discount_id")
    private Long id;
    private int percentage;

    @OneToMany(mappedBy = "discounts", cascade = CascadeType.ALL)
    private List<Product> products;
}
