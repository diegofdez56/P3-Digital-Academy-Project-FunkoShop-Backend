package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private String name;
    @Column(name = "image")
    private Optional<String> imageHash;
    private String description;
    private float price;
    private int stock;
    @Column(name = "is_available")
    private boolean isAvailable;

    
}
