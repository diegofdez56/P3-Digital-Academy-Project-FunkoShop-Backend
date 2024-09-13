package org.factoriaf5.digital_academy.funko_shop.role;

import java.util.Set;

import org.factoriaf5.digital_academy.funko_shop.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
