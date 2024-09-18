package org.factoriaf5.digital_academy.funko_shop.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class ProfileServiceTest {

    private ProfileService profileService;
    private ProfileRepository profileRepository;

    @BeforeEach
    public void setUp() {
        profileRepository = mock(ProfileRepository.class);
        profileService = new ProfileService(profileRepository);
    }

    // TODO: testGetProfileById
    // @Test
    // public void testGetProfileById() {
    //     Profile profile = new Profile();
    //     profile.setId(1L);
    //     when(profileRepository.findById(1L)).thenReturn(profile);
    //     Optional<Profile> result = profileService.getProfileById(1L);
    //     assertTrue(result.isPresent());
    //     assertEquals(1L, result.get().getId());
    // }

    @Test
    public void testCreateProfile() {
        Profile profile = new Profile();
        profile.setFirstName("John");
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile result = profileService.createProfile(profile);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

}