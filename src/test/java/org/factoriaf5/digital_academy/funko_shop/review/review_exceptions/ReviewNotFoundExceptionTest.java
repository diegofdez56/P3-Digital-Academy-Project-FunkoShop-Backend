package org.factoriaf5.digital_academy.funko_shop.review.review_exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReviewNotFoundExceptionTest {

    @Test
    void constructor_ShouldSetMessage() {
        String message = "Review not found";

        // Crear una instancia de ReviewNotFoundException usando el constructor con mensaje
        ReviewNotFoundException exception = new ReviewNotFoundException(message);

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_ShouldSetMessageAndCause() {
        String message = "Review not found with cause";
        Throwable cause = new RuntimeException("This is the cause");

        // Crear una instancia de ReviewNotFoundException usando el constructor con mensaje y causa
        ReviewNotFoundException exception = new ReviewNotFoundException(message, cause);

        // Verificar que el mensaje y la causa son los esperados
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowReviewNotFoundException() {
        // Verificar que se lanza ReviewNotFoundException con el mensaje correcto
        assertThrows(ReviewNotFoundException.class, () -> {
            throw new ReviewNotFoundException("Expected exception");
        });
    }
}
