package org.factoriaf5.digital_academy.funko_shop.profile;

import org.factoriaf5.digital_academy.funko_shop.profile.profile_exceptions.ProfileNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;
import org.factoriaf5.digital_academy.funko_shop.user.UserRepository;
import org.factoriaf5.digital_academy.funko_shop.user.user_exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    private UserRepository userRepository;

    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        User user = userRepository.findById(profileDTO.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Profile profile = new Profile();
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setStreet(profileDTO.getStreet());
        profile.setCity(profileDTO.getCity());
        profile.setRegion(profileDTO.getRegion());
        profile.setPostalCode(profileDTO.getPostalCode());
        profile.setCountry(profileDTO.getCountry());
        profile.isShipping();
        profile.isSubscribed();
        profile.setUser(user);

        Profile savedProfile = profileRepository.save(profile);
        return mapToDTO(savedProfile);
    }

    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProfileDTO getProfileById(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
        return mapToDTO(profile);
    }

    public ProfileDTO updateProfile(Long profileId, ProfileDTO profileDTO) {
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        existingProfile.setFirstName(profileDTO.getFirstName());
        existingProfile.setLastName(profileDTO.getLastName());
        existingProfile.setPhoneNumber(profileDTO.getPhoneNumber());
        existingProfile.setStreet(profileDTO.getStreet());
        existingProfile.setCity(profileDTO.getCity());
        existingProfile.setRegion(profileDTO.getRegion());
        existingProfile.setPostalCode(profileDTO.getPostalCode());
        existingProfile.setCountry(profileDTO.getCountry());
        existingProfile.isShipping();
        existingProfile.isSubscribed();

        Profile updatedProfile = profileRepository.save(existingProfile);
        return mapToDTO(updatedProfile);
    }

    public void deleteProfile(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new ProfileNotFoundException("Profile not found");
        }
        profileRepository.deleteById(profileId);

    }

    private ProfileDTO mapToDTO(Profile profile) {

        UserDTO userDTO = profile.getUser() != null ? new UserDTO(
                profile.getUser().getId(),
                profile.getUser().getEmail(),
                profile.getUser().getPassword(), null, null, null, null, null, null)
                : null;

        return new ProfileDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhoneNumber(),
                profile.getStreet(),
                profile.getCity(),
                profile.getRegion(),
                profile.getPostalCode(),
                profile.getCountry(),
                profile.isShipping(),
                profile.isSubscribed(),
                userDTO, null);

    }
}