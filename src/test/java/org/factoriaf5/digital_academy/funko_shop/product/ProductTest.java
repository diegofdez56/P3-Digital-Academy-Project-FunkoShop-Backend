package org.factoriaf5.digital_academy.funko_shop.product;

import static org.junit.jupiter.api.Assertions.*;
import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.discount.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;
    private Category category;
    private Discount discount;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Category 1");

        discount = new Discount();
        discount.setId(1L);
        discount.setPercentage(10);

        product = Product.builder()
                .id(1L)
                .name("Funko Pop")
                .description("A Funko Pop figure")
                .price(15.99f)
                .stock(100)
                .isAvailable(true)
                .category(category)
                .discount(discount)
                .build();
    }

    @Test
    public void testProductCreation() {
        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Funko Pop", product.getName());
        assertEquals("A Funko Pop figure", product.getDescription());
        assertEquals(15.99f, product.getPrice());
        assertEquals(100, product.getStock());
        assertTrue(product.isAvailable());
        assertEquals(category, product.getCategory());
        assertEquals(discount, product.getDiscount());
    }

    @Test
    public void testProductSetters() {
        product.setName("New Funko Pop");
        product.setDescription("A new Funko Pop figure");
        product.setPrice(20.99f);
        product.setStock(50);
        product.setAvailable(false);

        assertEquals("New Funko Pop", product.getName());
        assertEquals("A new Funko Pop figure", product.getDescription());
        assertEquals(20.99f, product.getPrice());
        assertEquals(50, product.getStock());
        assertFalse(product.isAvailable());
    }

    @Test
    public void testProductCategory() {
        Category newCategory = new Category();
        newCategory.setId(2L);
        newCategory.setName("Category 2");

        product.setCategory(newCategory);

        assertEquals(newCategory, product.getCategory());
    }

    @Test
    public void testProductDiscount() {
        Discount newDiscount = new Discount();
        newDiscount.setId(2L);
        newDiscount.setPercentage(20);

        product.setDiscount(newDiscount);

        assertEquals(newDiscount, product.getDiscount());
    }
}