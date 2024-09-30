package org.factoriaf5.digital_academy.funko_shop.stripe;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api-endpoint}/payments")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        try {
            logger.info("Creating payment intent for amount: {}", paymentRequest.getAmount());

            if (paymentRequest.getAmount() <= 0) {
                logger.error("Invalid amount: {}", paymentRequest.getAmount());
                return ResponseEntity.badRequest().body("Invalid amount");
            }

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(paymentRequest.getAmount())
                    .setCurrency("eur")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            logger.info("Payment intent created: {}", paymentIntent.getId());

            return ResponseEntity.ok(new PaymentResponse(paymentIntent.getClientSecret()));

        } catch (Exception e) {
            logger.error("Error creating payment intent: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment intent");
        }
    }
}