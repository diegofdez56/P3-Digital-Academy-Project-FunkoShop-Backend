package org.factoriaf5.digital_academy.funko_shop.stripe;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @jakarta.annotation.PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }
}