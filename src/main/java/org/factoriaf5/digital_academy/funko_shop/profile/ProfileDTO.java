package org.factoriaf5.digital_academy.funko_shop.profile;

import org.factoriaf5.digital_academy.funko_shop.address.AddressDTO;
import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String street;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private boolean isShipping;
    private boolean isSubscribed;
    private UserDTO user;
    private AddressDTO address;
}
