package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;

import org.factoriaf5.digital_academy.funko_shop.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{category_id}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody Product product, @PathVariable Long category_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product, category_id));
    }

    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) int pageNum,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER) String sortOrder) {

        return ResponseEntity.ok(productService.getAllProducts(pageNum, pageSize, sortBy, sortOrder));
    }


    @GetMapping("/public/categories/{category_id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(
            @PathVariable Long category_id,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) int pageNum,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER) String sortOrder) {

        return ResponseEntity.ok(productService.getProductsByCategory(category_id, pageNum, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<List<ProductDTO>> getProductsByKeyword(
            @PathVariable String keyword,
            @RequestParam(name = "pageNum", defaultValue = AppConstants.PAGE_NUMBER) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder) {

        return ResponseEntity.ok(productService.searchProductsByKeyword(keyword, pageNum, pageSize, sortBy, sortOrder));
        
    }


    @PutMapping("/admin/products/{product_id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long product_id, @Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product_id, product));
    }    


    @DeleteMapping("/admin/products/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long product_id) {
        productService.deleteProduct(product_id);
        return ResponseEntity.noContent().build();
    }

}
