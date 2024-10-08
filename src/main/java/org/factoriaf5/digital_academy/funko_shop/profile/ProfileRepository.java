package org.factoriaf5.digital_academy.funko_shop.profile;

import java.util.Optional;
import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUser(User user);

}