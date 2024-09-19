package org.factoriaf5.digital_academy.funko_shop.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id);

}