package org.factoriaf5.digital_academy.funko_shop.review.review_exceptions;

public class ReviewException extends RuntimeException {

    public ReviewException(String message) {
        super(message);
    }

    public ReviewException(String message, Throwable cause) {
        super(message, cause);
    }
}
