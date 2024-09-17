package org.factoriaf5.digital_academy.funko_shop.account_settings;

import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSettingsDTO {
    
    private Long id;
    private String language;
    private UserDTO user;
}
