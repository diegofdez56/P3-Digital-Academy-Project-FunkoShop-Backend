package org.factoriaf5.digital_academy.funko_shop.category.category_exceptions;

public class TooManyCategoriesSelectedException extends RuntimeException {
    public TooManyCategoriesSelectedException(String message) {
        super(message);
    }
}
