package org.factoriaf5.digital_academy.funko_shop.stripe;

public class PaymentRequest {
    private long amount;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}