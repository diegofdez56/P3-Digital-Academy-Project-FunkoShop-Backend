package org.factoriaf5.digital_academy.funko_shop.review.review_exceptions;

public class ReviewNotFoundException extends ReviewException{
    
    public ReviewNotFoundException(String message) {
        super(message);
    }
    
    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
