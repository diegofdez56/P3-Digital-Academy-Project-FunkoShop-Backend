package org.factoriaf5.digital_academy.funko_shop.profile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class ProfileDTOTest {

    @Test
    void testProfileDTO() {
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        ProfileDTO profileDTO = new ProfileDTO(0, expectedFirstName, expectedLastName, expectedLastName, expectedLastName, expectedLastName, expectedLastName, expectedLastName, expectedLastName, false, false, null, null);

        assertEquals(expectedFirstName, profileDTO.getFirstName());
        assertEquals(expectedLastName, profileDTO.getLastName());
    }

    @Test
    void testSetName() {
        
        ProfileDTO profileDTO = new ProfileDTO();
        String expectedFirstName = "Jane";

        profileDTO.setFirstName(expectedFirstName);

        assertEquals(expectedFirstName, profileDTO.getFirstName());
    }

}