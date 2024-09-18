package org.factoriaf5.digital_academy.funko_shop.role;

import java.util.Set;

import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    
    private Long id;
    private String name;
    private Set<UserDTO> users;

}
