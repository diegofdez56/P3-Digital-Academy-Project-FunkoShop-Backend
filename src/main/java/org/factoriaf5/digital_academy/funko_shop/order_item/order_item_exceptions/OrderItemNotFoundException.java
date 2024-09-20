package org.factoriaf5.digital_academy.funko_shop.order_item.order_item_exceptions;

public class OrderItemNotFoundException extends OrderItemException{
    
    
    public OrderItemNotFoundException(String message) {
        super(message);
    }
    
    public OrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
