package org.factoriaf5.digital_academy.funko_shop.profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findById(long id);
}