package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;

public interface ProductService {

    
    ProductDTO addProduct(Product product, Long category_id);

    List<ProductDTO> getAllProducts(Integer pageNum, Integer pageSize, String sortBy, String sortOrder);

    List<ProductDTO> searchProductsByKeyword(String keyword, Integer pageNum, Integer pageSize, String sortBy, String sortOrder);

    List<ProductDTO> getProductsByCategory(Long category_id, Integer pageNum, Integer pageSize, String sortBy, String sortOrder);

    ProductDTO updateProduct(Long product_id, Product product);

    void deleteProduct(Long product_id);
}
