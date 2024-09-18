package org.factoriaf5.digital_academy.funko_shop.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.factoriaf5.digital_academy.funko_shop.account_settings.AccountSettings;
import org.factoriaf5.digital_academy.funko_shop.cart.Cart;
import org.factoriaf5.digital_academy.funko_shop.order.Order;
import org.factoriaf5.digital_academy.funko_shop.profile.Profile;
import org.factoriaf5.digital_academy.funko_shop.review.Review;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    /*
     * @ManyToMany(fetch = FetchType.EAGER)
     * 
     * @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
     * inverseJoinColumns = @JoinColumn(name = "role_id"))
     * Set<Role> roles;
     */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "user")
    private AccountSettings accountSettings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
