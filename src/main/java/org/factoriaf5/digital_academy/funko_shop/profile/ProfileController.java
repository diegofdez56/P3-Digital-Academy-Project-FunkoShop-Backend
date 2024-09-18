package org.factoriaf5.digital_academy.funko_shop.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    private ProfileRepository profileRepository;

    

    private ProfileDTO convertToDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setLastName(profile.getLastName());
        profileDTO.setStreet(profile.getStreet());
        profileDTO.setPhoneNumber(profile.getPhoneNumber());
        profileDTO.setCity(profile.getCity());
        profileDTO.setRegion(profile.getRegion());
        profileDTO.setPostalCode(profile.getPostalCode());
        profileDTO.setCountry(profile.getCountry());
        return profileDTO;
    }

    private Profile convertToEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setStreet(profileDTO.getStreet());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setCity(profileDTO.getCity());
        profile.setRegion(profileDTO.getRegion());
        profile.setPostalCode(profileDTO.getPostalCode());
        profile.setCountry(profileDTO.getCountry());

        return profile;
    }

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
       
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(profile -> ResponseEntity.ok(convertToDTO(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProfileDTO createProfile(@RequestBody ProfileDTO profileDTO) {
        Profile profile = convertToEntity(profileDTO);
        Profile createdProfile = profileService.createProfile(profile);
        return convertToDTO(createdProfile);
    }

    @PutMapping("/{profile_id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long profile_id,
            @Valid @RequestBody ProfileDTO profileDTO) {
        Profile updatedProfile = profileService.updateProfile(profile_id, profileDTO);
        return ResponseEntity.ok(convertToDTO(updatedProfile));
    }

    @DeleteMapping("/{profile_id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profile_id) {
        profileService.deleteProfile(profile_id);
        return ResponseEntity.noContent().build();

    }

}