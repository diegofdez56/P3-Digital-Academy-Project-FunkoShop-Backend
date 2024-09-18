package org.factoriaf5.digital_academy.funko_shop.order.order_exceptions;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException(String message) {
        super(message);
    }
    
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
