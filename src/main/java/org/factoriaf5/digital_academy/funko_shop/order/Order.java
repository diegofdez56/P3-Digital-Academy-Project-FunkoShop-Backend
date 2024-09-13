package org.factoriaf5.digital_academy.funko_shop.order;

import org.factoriaf5.digital_academy.funko_shop.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    private String status;
    @Column(name = "total_price")
    private float totalPrice;
    @Column(name = "total_items")
    private int totalItems;
    @Column(name = "is_paid")
    private boolean isPaid;

    @ManyToOne //revisar esto
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    
}
