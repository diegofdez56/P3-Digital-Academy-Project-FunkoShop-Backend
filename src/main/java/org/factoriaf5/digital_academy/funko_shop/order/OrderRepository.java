package org.factoriaf5.digital_academy.funko_shop.order;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
