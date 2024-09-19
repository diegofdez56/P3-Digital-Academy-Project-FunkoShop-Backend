package org.factoriaf5.digital_academy.funko_shop.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Mock
    private DiscountRepository discountRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void applyDiscountActiveTest() {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setPercentage(20);
        discountDTO.setActive(true);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(100);
        productDTO.setDiscount(discountDTO);

        float discountedPrice = discountService.applyDiscount(productDTO);

        assertEquals(80, discountedPrice, 0.01);
    }

    @Test
    public void applyDiscountInactiveTest() {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setPercentage(20);
        discountDTO.setActive(false);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(100);
        productDTO.setDiscount(discountDTO);

        float discountedPrice = discountService.applyDiscount(productDTO);

        assertEquals(100, discountedPrice, 0.01);
    }

    @Test
    public void applyDiscountWithNoDiscount() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(100);
        productDTO.setDiscount(null);

        float discountedPrice = discountService.applyDiscount(productDTO);

        assertEquals(100, discountedPrice, 0.01);
    }
}
