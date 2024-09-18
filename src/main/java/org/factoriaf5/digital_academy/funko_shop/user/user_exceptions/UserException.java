package org.factoriaf5.digital_academy.funko_shop.user.user_exceptions;

public class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
