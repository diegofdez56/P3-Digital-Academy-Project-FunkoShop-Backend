package org.factoriaf5.digital_academy.funko_shop.order;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItem;
import org.factoriaf5.digital_academy.funko_shop.tracking.Tracking;
import org.factoriaf5.digital_academy.funko_shop.user.User;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private String status;
    private float totalPrice;
    private int totalItems;
    private boolean isPaid;
    private User user;   
    private List<OrderItem> orderItems;    
    private Tracking tracking;
}
