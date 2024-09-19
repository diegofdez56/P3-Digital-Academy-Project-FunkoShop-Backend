package org.factoriaf5.digital_academy.funko_shop.category;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CategoryTest {

    @Test
    public void testCategoryName() {
        Category category = new Category(null, "Action Figures", null, null);
        assertEquals("Action Figures", category.getName());
    }

    @Test
    public void testCategoryId() {
        Category category = new Category(null, "Action Figures", null, null);
        category.setId(1L);
        assertEquals(1, category.getId());
    }
}
