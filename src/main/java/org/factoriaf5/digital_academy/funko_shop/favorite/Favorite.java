package org.factoriaf5.digital_academy.funko_shop.favorite;

import org.factoriaf5.digital_academy.funko_shop.product.Product;
import org.factoriaf5.digital_academy.funko_shop.user.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "wishlist")
public class Favorite {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "wishlist_id")
private Long id;

@ManyToOne
@JoinColumn(name = "product_id", referencedColumnName = "product_id")
private Product product;

@ManyToOne
@JoinColumn(name = "user_id", referencedColumnName = "user_id")
private User user;
}
