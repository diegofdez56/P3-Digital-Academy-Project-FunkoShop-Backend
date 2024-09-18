package org.factoriaf5.digital_academy.funko_shop.tracking;

import org.factoriaf5.digital_academy.funko_shop.order.OrderDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingDTO {
    
    private Long id;
    private String trackingNumber;
    private OrderDTO order;
}
