package org.factoriaf5.digital_academy.funko_shop.stripe;

import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    public Session createCheckoutSession(List<Map<String, Object>> cartItems) throws Exception {
        SessionCreateParams.Builder sessionBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel");

        for (Map<String, Object> item : cartItems) {
            String productName = (String) item.get("name");
            long unitAmount = ((Number) item.get("price")).longValue();
            long quantity = ((Number) item.get("quantity")).longValue();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(quantity)
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("eur")
                                    .setUnitAmount(unitAmount)
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(productName)
                                                    .build())
                                    .build())
                    .build();

            sessionBuilder.addLineItem(lineItem);
        }

        SessionCreateParams params = sessionBuilder.build();
        return Session.create(params);
    }
}