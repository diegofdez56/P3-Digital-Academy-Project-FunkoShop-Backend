package org.factoriaf5.digital_academy.funko_shop.profile.profile_exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileNotFoundExceptionTest {

    @Test
    public void testProfileNotFoundException() {
        assertThrows(ProfileNotFoundException.class, () -> {
            throw new ProfileNotFoundException("Profile not found");
        });
    }
}
