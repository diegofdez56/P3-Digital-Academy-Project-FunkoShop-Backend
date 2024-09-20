package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.config.AppConstants;
import org.factoriaf5.digital_academy.funko_shop.product.product_exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api-endpoint}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
        Long categoryId = productDto.getCategory() != null ? productDto.getCategory().getId() : null;
        Long discountId = productDto.getDiscount() != null ? productDto.getDiscount().getId() : null;

        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        if (discountId == null) {
            System.out.println("Discount ID is null, but that might be okay.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productDto, categoryId, discountId));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) int pageNum,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER) String sortOrder) {

        return ResponseEntity.ok(productService.getAllProducts(pageNum, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            ProductDTO product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(
            @PathVariable Long id,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) int pageNum,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER) String sortOrder) {

        return ResponseEntity
                .ok(productService.getProductsByCategory(id, pageNum, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<ProductDTO>> getProductsByKeyword(
            @PathVariable String keyword,
            @RequestParam(name = "pageNum", defaultValue = AppConstants.PAGE_NUMBER) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder) {

        return ResponseEntity.ok(productService.searchProductsByKeyword(keyword, pageNum, pageSize, sortBy, sortOrder));
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
            @Valid @RequestBody ProductDTO productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
