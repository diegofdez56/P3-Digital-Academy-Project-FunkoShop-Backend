package org.factoriaf5.digital_academy.funko_shop.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    
}
