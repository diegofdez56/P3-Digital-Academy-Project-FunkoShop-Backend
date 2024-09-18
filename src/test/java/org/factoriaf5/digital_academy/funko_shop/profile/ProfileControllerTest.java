package org.factoriaf5.digital_academy.funko_shop.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    void getAllProfiles() throws Exception {
        when(profileService.getAllProfiles()).thenReturn(Arrays.asList(new Profile(), new Profile()));

        mockMvc.perform(get("/profiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(profileService, times(1)).getAllProfiles();
    }

    @Test
    void getProfileById() throws Exception {
        Profile profile = new Profile();
        profile.setId(1L);
        when(profileService.getProfileById(anyLong())).thenReturn(Optional.of(profile));

        mockMvc.perform(get("/profiles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(profileService, times(1)).getProfileById(1L);
    }

    @Test
    void createProfile() throws Exception {
        Profile profile = new Profile();
        profile.setId(1L);
        when(profileService.createProfile(any(Profile.class))).thenReturn(profile);

        mockMvc.perform(post("/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Profile\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(profileService, times(1)).createProfile(any(Profile.class));
    }

    // TODO: Implement the test for the updateProfile method
    // @Test
    // void updateProfile() throws Exception {
    //     ProfileDTO profileDTO = new ProfileDTO();
    //     profileDTO.setId(1L);
    //     when(profileService.updateProfile(anyLong(), any(ProfileDTO.class))).thenReturn(profileDTO);

    //     mockMvc.perform(put("/profiles/1")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content("{\"name\":\"Updated Profile\"}"))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.id").value(1L));

    //     verify(profileService, times(1)).updateProfile(anyLong(), any(ProfileDTO.class));
    // }

    // TODO: Implement the test for the deleteProfile method
    // @Test
    // void deleteProfile() throws Exception {
    //     doNothing().when(profileService).deleteProfile(anyLong());

    //     mockMvc.perform(delete("/profiles/1"))
    //             .andExpect(status().isOk());

    //     verify(profileService, times(1)).deleteProfile(anyLong());
    // }
}