package org.factoriaf5.digital_academy.funko_shop.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.factoriaf5.digital_academy.funko_shop.account_settings.AccountSettings;
import org.factoriaf5.digital_academy.funko_shop.cart.Cart;
import org.factoriaf5.digital_academy.funko_shop.order.Order;
import org.factoriaf5.digital_academy.funko_shop.profile.Profile;
import org.factoriaf5.digital_academy.funko_shop.review.Review;
import org.factoriaf5.digital_academy.funko_shop.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

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

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

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
/*
 * @Entity
 * 
 * @Builder
 * 
 * @Data
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * 
 * @Table(name = "users")
 * public class User implements UserDetails {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * 
 * @Column(name = "user_id")
 * private Integer id;
 * 
 * private String firstname;
 * private String lastname;
 * 
 * @NonNull
 * private String email;
 * 
 * @NonNull
 * private String password;
 * 
 * @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
 * private Profile profile;
 * 
 * @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 * private List<Review> reviews;
 * 
 * @OneToOne(mappedBy = "user")
 * private AccountSettings accountSettings;
 * 
 * @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 * private List<Order> orders;
 * 
 * @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
 * private Cart cart;
 * 
 * @OneToMany(mappedBy = "user")
 * private List<Token> tokens;
 * 
 * @Enumerated(EnumType.STRING)
 * private Role role;
 * 
 * @Override
 * public Collection<? extends GrantedAuthority> getAuthorities() {
 * return role.getAuthorities();
 * }
 * 
 * @Override
 * public String getPassword() {
 * return password;
 * }
 * 
 * @Override
 * public String getUsername() {
 * return email;
 * }
 * 
 * @Override
 * public boolean isAccountNonExpired() {
 * return true;
 * }
 * 
 * @Override
 * public boolean isAccountNonLocked() {
 * return true;
 * }
 * 
 * @Override
 * public boolean isCredentialsNonExpired() {
 * return true;
 * }
 * 
 * @Override
 * public boolean isEnabled() {
 * return true;
 * }
 * 
 * }
 */
