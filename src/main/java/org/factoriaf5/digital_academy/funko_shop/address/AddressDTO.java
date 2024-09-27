package org.factoriaf5.digital_academy.funko_shop.address;

import org.factoriaf5.digital_academy.funko_shop.profile.ProfileDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private long id;
    private String street;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private ProfileDTO profile;
}
