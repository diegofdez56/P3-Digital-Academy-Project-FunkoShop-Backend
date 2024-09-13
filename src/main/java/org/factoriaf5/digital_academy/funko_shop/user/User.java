package org.factoriaf5.digital_academy.funko_shop.user;

import java.util.Set;

import org.factoriaf5.digital_academy.funko_shop.account_settings.AccountSettings;
import org.factoriaf5.digital_academy.funko_shop.review.Review;
import org.factoriaf5.digital_academy.funko_shop.role.Role;
import org.springframework.context.annotation.Profile;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @NonNull
    private String email;
    @NonNull
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile profile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "review_id")
    Set<Review> reviews;

    @OneToOne
    @JoinColumn(name = "account_settings_id")
    private AccountSettings accountSettings;

}
