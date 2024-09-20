package org.factoriaf5.digital_academy.funko_shop.order_item.order_item_exceptions;

public class OrderItemException extends RuntimeException{    
    
    public OrderItemException(String message) {
        super(message);
    }

    public OrderItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
