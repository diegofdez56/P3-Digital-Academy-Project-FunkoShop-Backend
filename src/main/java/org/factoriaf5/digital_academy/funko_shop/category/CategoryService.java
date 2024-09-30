package org.factoriaf5.digital_academy.funko_shop.category;

import org.factoriaf5.digital_academy.funko_shop.category.category_exceptions.CategoryNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.product.Product;
import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;
import org.factoriaf5.digital_academy.funko_shop.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        return convertToDTO(category);
    }

    public List<ProductDTO> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);

        return products.stream()
            .map(this::mapToProductDTO)  
            .collect(Collectors.toList());
    }

   private ProductDTO mapToProductDTO(Product product) {

    CategoryDTO categoryDTO = new CategoryDTO(
        product.getCategory().getId(), 
        product.getCategory().getName(), 
        product.getCategory().getImageHash()
    );

    return new ProductDTO(
        product.getId(),
        product.getName(),
        product.getImageHash(),
        product.getDescription(),
        product.getPrice(),
        product.getDiscountedPrice(),
        product.getStock(),
        product.isAvailable(),
        product.isNew(),
        categoryDTO,
        product.getDiscount()  
    );
}

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getImageHash());
    }
}
