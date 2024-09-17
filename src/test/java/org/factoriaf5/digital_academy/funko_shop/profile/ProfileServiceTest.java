package org.factoriaf5.digital_academy.funko_shop.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProfileById() {
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setFirstName("Name");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

        Optional<Profile> result = profileService.getProfileById(1L);

        assertNotNull(result);
        assertEquals("Name", result.get().getFirstName());
    }

    @Test
    void testCreateProfile() {
        Profile profile = new Profile();
        profile.setFirstName("Neimu");

        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile result = profileService.createProfile(profile);

        assertNotNull(result);
        assertEquals("Neimu", result.getFirstName());
    }
}