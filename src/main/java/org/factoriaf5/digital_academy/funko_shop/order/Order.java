package org.factoriaf5.digital_academy.funko_shop.order;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItem;
import org.factoriaf5.digital_academy.funko_shop.tracking.Tracking;
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name = "tracking_id")
    private Tracking tracking;
    
    
}
