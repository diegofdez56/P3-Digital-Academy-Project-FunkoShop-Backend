package org.factoriaf5.digital_academy.funko_shop.stripe;

import java.util.Map;
import java.util.HashMap;
import lombok.Data;

@Data
public class StripeChargeDto {
    
    private String stripeToken;
    private String username;
    private Double amount;
    private Boolean success;
    private String message;
    private String chargeId;
    private Map<String, Object> additionalInfo = new HashMap<>();
}
