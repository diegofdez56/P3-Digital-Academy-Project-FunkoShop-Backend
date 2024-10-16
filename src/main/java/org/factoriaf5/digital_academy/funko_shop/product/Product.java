package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItem;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @NonNull
    private String name;
    @Column(name = "image")
    private String imageHash;
    @Column(name = "image_2")
    private String imageHash2;
    @NonNull
    private String description;
    private float price;
    private int stock;
   
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(nullable = true)
    private int discount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE) 
    private Date createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

}
