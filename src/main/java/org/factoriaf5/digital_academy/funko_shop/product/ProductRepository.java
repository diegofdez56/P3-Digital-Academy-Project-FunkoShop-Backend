package org.factoriaf5.digital_academy.funko_shop.product;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.discount > 0")
    List<Product> findByDiscount();

    @Query("SELECT p FROM Product p WHERE p.createdAt >= :cutoffDate")
    List<Product> findNewProducts(Date cutoffDate);

    List<Product> findByCategoryId(Long categoryId);

}
