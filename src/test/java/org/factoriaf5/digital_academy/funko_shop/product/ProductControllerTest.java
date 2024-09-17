package org.factoriaf5.digital_academy.funko_shop.product;

import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productDTO = new ProductDTO(1L, "Funko Pop", "imageHash", "Funko Pop Description", 19.99f, 100, true, null, null);
    }

   @Test
public void testAddProduct() throws Exception {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("Funko Pop");
    productDTO.setPrice(19.99f);
    productDTO.setStock(10);
    productDTO.setAvailable(true);

    CategoryDTO categoryDTO = new CategoryDTO(1L, "Category Name", "hash123");
    productDTO.setCategory(categoryDTO);

    when(productService.addProduct(any(ProductDTO.class), anyLong(), any())).thenReturn(productDTO);

    ResponseEntity<ProductDTO> response = productController.addProduct(productDTO);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(productDTO, response.getBody());
    verify(productService, times(1)).addProduct(any(ProductDTO.class), anyLong(), any());
}

    @Test
    public void testGetAllProducts() {
        List<ProductDTO> productList = Arrays.asList(productDTO);

        when(productService.getAllProducts(0, 10, "name", "asc")).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts(0, 10, "name", "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productService, times(1)).getAllProducts(0, 10, "name", "asc");
    }

    @Test
    public void testGetProductsByCategory() {
        List<ProductDTO> productList = Arrays.asList(productDTO);

        when(productService.getProductsByCategory(1L, 0, 10, "name", "asc")).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> response = productController.getProductsByCategory(1L, 0, 10, "name", "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productService, times(1)).getProductsByCategory(1L, 0, 10, "name", "asc");
    }

    @Test
    public void testGetProductsByKeyword() {
        List<ProductDTO> productList = Arrays.asList(productDTO);

        when(productService.searchProductsByKeyword("Funko", 0, 10, "name", "asc")).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> response = productController.getProductsByKeyword("Funko", 0, 10, "name", "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productService, times(1)).searchProductsByKeyword("Funko", 0, 10, "name", "asc");
    }

  @Test
public void testUpdateProduct() throws Exception {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("Updated Funko Pop");
    productDTO.setPrice(19.99f);
    productDTO.setStock(8);
    productDTO.setAvailable(true);

    when(productService.updateProduct(anyLong(), any(ProductDTO.class))).thenReturn(productDTO);

    ResponseEntity<ProductDTO> response = productController.updateProduct(1L, productDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(productDTO, response.getBody());
    verify(productService, times(1)).updateProduct(anyLong(), any(ProductDTO.class));
}

    @Test
    public void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }
}
