package org.factoriaf5.digital_academy.funko_shop.order_item;

import org.factoriaf5.digital_academy.funko_shop.order.Order;
import org.factoriaf5.digital_academy.funko_shop.product.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    
    @Id
    @GeneratedValue 
    @Column(name = "order_item_id")
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
