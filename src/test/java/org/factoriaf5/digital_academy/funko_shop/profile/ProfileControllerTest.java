package org.factoriaf5.digital_academy.funko_shop.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks

    private ProfileController profileController;

    private ProfileDTO profileDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        profileDTO = new ProfileDTO(1L, "Mauricio", "Colmenero", "666696969", "Esperanza Sur", "Madrid", "Madrid",
                "28000", "Spain", true, true, null, null);
    }

    @Test
    public void testCreateProfile() throws Exception {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName("Mauricio");
        profileDTO.setLastName("Colmenero");
        profileDTO.setPhoneNumber("666696969");
        profileDTO.setStreet("Esperanza Sur");
        profileDTO.setCity("Madrid");
        profileDTO.setRegion("Madrid");
        profileDTO.setPostalCode("28000");
        profileDTO.setCountry("Spain");
        profileDTO.setShipping(true);
        profileDTO.setSubscribed(true);

        when(profileService.createProfile(any(ProfileDTO.class))).thenReturn(profileDTO);

        ResponseEntity<ProfileDTO> response = profileController.createProfile(profileDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(profileDTO, response.getBody());
        verify(profileService, times(1)).createProfile(any(ProfileDTO.class));
    }

    @Test
    public void testGetAllProfiles() {
        when(profileService.getAllProfiles()).thenReturn(null);
        profileController.getAllProfiles();
        verify(profileService, times(1)).getAllProfiles();
    }

    @Test
    public void testGetProfileById() {
        when(profileService.getProfileById(1L)).thenReturn(profileDTO);
        profileController.getProfileById(1L);
        verify(profileService, times(1)).getProfileById(1L);
    }

    @Test
    public void testUpdateProfile() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName("Mauricio");
        profileDTO.setLastName("Colmenero");
        profileDTO.setPhoneNumber("666696969");
        profileDTO.setStreet("Esperanza Sur");
        profileDTO.setCity("Madrid");
        profileDTO.setRegion("Madrid");
        profileDTO.setPostalCode("28000");
        profileDTO.setCountry("España");
        profileDTO.setShipping(true);
        profileDTO.setSubscribed(true);

        when(profileService.updateProfile(1L, profileDTO)).thenReturn(profileDTO);

        ResponseEntity<ProfileDTO> response = profileController.updateProfile(1L, profileDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(profileDTO, response.getBody());
        verify(profileService, times(1)).updateProfile(1L, profileDTO);
    }

    @Test
    public void testDeleteProfile() {
        
        doNothing().when(profileService).deleteProfile(1L);
        
        ResponseEntity<Void> response = profileController.deleteProfile(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(profileService, times(1)).deleteProfile(1L);
    }

}
