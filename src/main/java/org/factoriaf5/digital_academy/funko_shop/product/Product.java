package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.cart_item.CartItem;
import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.discount.Discount;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItem;

import jakarta.persistence.*;
import lombok.*;

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
    @NonNull
    private String description;
    private float price;
    private int stock;
    @Column(name = "is_available", columnDefinition = "boolean default true")
    private boolean isAvailable;

    @Column(name = "is_new", columnDefinition = "boolean default true")
    private boolean isNew;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", nullable = true)
    private Discount discount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public float getDiscountedPrice() {
        if (discount == null || !discount.isActive()) {
            return price;
        }
        return price * (1 - discount.getPercentage() / 100);
    }

}
