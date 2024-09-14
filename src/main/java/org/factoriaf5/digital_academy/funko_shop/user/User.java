package org.factoriaf5.digital_academy.funko_shop.user;

import java.util.List;
import java.util.Set;

import org.factoriaf5.digital_academy.funko_shop.account_settings.AccountSettings;
import org.factoriaf5.digital_academy.funko_shop.cart.Cart;
import org.factoriaf5.digital_academy.funko_shop.order.Order;
import org.factoriaf5.digital_academy.funko_shop.profile.UserProfile;
import org.factoriaf5.digital_academy.funko_shop.review.Review;
import org.factoriaf5.digital_academy.funko_shop.role.Role;

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
    private UserProfile profile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "user")
    private AccountSettings accountSettings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

}
