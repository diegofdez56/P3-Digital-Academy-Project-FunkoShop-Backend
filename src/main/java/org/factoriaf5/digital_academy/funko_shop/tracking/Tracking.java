package org.factoriaf5.digital_academy.funko_shop.tracking;

import org.factoriaf5.digital_academy.funko_shop.order.Order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tracking")
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_id")
    private Long id;
    @Column(name = "tracking_number")
    private String trackingNumber;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

}
