package org.factoriaf5.digital_academy.funko_shop.address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address save(AddressDTO address);
    
    

}
