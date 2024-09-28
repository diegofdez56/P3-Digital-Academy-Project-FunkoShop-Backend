package org.factoriaf5.digital_academy.funko_shop.stripe;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

import org.factoriaf5.digital_academy.funko_shop.cart_item.CartItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RequestMapping("${api-endpoint}/checkout")
public class StripeController {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> createCheckoutSession(@RequestBody List<CartItem> items) {
        
        try {
            SessionCreateParams.Builder sessionBuilder = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/return.html?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl("http://localhost:8080/checkout.html");

            for (CartItem item : items) {
                SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("eur")
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getProduct().getName())
                                                        .addImage(item.getProduct().getImageHash())
                                                        .build())
                                        .setUnitAmount((long) (item.getPrice() * 100))
                                        .build())
                        .setQuantity((long) item.getQuantity())
                        .build();

                sessionBuilder.addLineItem(lineItem);
            }

            SessionCreateParams params = sessionBuilder.build();

            Session session = Session.create(params);
            return ResponseEntity.ok(session.getId());

        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso no autorizado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating session: " + e.getMessage());
        }
    }

    @GetMapping("/session-status")
    public ResponseEntity<Session> getSessionStatus(@RequestParam String session_id) {
        try {
            Session session = Session.retrieve(session_id);
            return ResponseEntity.ok(session);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
