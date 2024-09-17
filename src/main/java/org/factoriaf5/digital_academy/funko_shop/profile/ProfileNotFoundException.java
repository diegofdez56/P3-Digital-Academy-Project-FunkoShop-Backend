package org.factoriaf5.digital_academy.funko_shop.profile;

public class ProfileNotFoundException extends ProfileException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
    
    public ProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
