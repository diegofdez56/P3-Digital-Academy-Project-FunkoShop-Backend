package org.factoriaf5.digital_academy.funko_shop.profile;

import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

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
        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setId(1L);
        ProfileDTO profileDTO2 = new ProfileDTO();
        profileDTO2.setId(2L);
        when(profileService.getAllProfiles()).thenReturn(Arrays.asList(profileDTO1, profileDTO2));

        mockMvc.perform(get("/profiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));

        verify(profileService, times(1)).getAllProfiles();
    }

    @Test
    void getProfileById() throws Exception {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(1L);
        when(profileService.getProfileById(anyLong())).thenReturn(profileDTO);

        mockMvc.perform(get("/profiles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(profileService, times(1)).getProfileById(1L);
    }

    @Test
    void createProfile() throws Exception {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(1L);
        profileDTO.setUser(new UserDTO()); // Ensure this is set up correctly
        when(profileService.createProfile(any(ProfileDTO.class))).thenReturn(profileDTO);

        mockMvc.perform(post("/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1, \"user\": {\"id\": 1}}")) // Adjust JSON content to match ProfileDTO
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(profileService, times(1)).createProfile(any(ProfileDTO.class));
    }

    @Test
    void updateProfile() throws Exception {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(1L);
        profileDTO.setUser(new UserDTO()); // Ensure this is set up correctly
        when(profileService.updateProfile(anyLong(), any(ProfileDTO.class))).thenReturn(profileDTO);

        mockMvc.perform(put("/profiles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1, \"user\": {\"id\": 1}}")) // Adjust JSON content to match ProfileDTO
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(profileService, times(1)).updateProfile(anyLong(), any(ProfileDTO.class));
    }

    @Test
    void deleteProfile() throws Exception {
        doNothing().when(profileService).deleteProfile(anyLong());

        mockMvc.perform(delete("/profiles/1"))
                .andExpect(status().isNoContent());

        verify(profileService, times(1)).deleteProfile(anyLong());
    }
}
