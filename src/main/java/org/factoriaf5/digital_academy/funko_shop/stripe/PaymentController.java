package org.factoriaf5.digital_academy.funko_shop.stripe;

import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api-endpoint}/payment")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/checkout")
    public Map<String, String> createCheckoutSession(@RequestBody List<Map<String, Object>> cartItems)
            throws Exception {
        try {
            Session session = paymentService.createCheckoutSession(cartItems);
            return Map.of("sessionId", session.getId());
        } catch (Exception e) {
            System.err.println("Error al crear la sesión de pago: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar el pago");
        }
    }

    @GetMapping("/success")
    public String paymentSuccess() {
        return "Payment completed successfully. Thank you for your purchase!";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "Payment was cancelled. You can try again.";
    }
}