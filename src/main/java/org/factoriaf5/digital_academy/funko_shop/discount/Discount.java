package org.factoriaf5.digital_academy.funko_shop.discount;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = true)
    private Long id;
    private float percentage;
    private boolean isActive;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
    private List<Product> products;

}
