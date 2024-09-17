package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;
import java.util.stream.Collectors;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryRepository;
import org.factoriaf5.digital_academy.funko_shop.discount.Discount;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountDTO;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DiscountRepository discountRepository;

    public ProductDTO addProduct(ProductDTO productDto, Long categoryId, Long discountId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Discount discount = discountId != null ? discountRepository.findById(discountId)
            .orElseThrow(() -> new IllegalArgumentException("Discount not found")) : null;

        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageHash(productDto.getImageHash());
        product.setAvailable(productDto.isAvailable());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(category);
        product.setDiscount(discount);

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    public List<ProductDTO> getAllProducts(int pageNum, int pageSize, String sortBy, String sortOrder) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return productRepository.findAll(pageRequest).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId, int pageNum, int pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return productRepository.findByCategory(category, pageRequest).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public List<ProductDTO> searchProductsByKeyword(String keyword, int pageNum, int pageSize, String sortBy, String sortOrder) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return productRepository.findByNameContainingIgnoreCase(keyword, pageRequest).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(Long productId, ProductDTO productDto) {
        Product existingProduct = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingProduct.setName(productDto.getName());
        existingProduct.setImageHash(productDto.getImageHash());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setAvailable(productDto.isAvailable());

        // Handle category and discount updates if needed
        if (productDto.getCategory() != null) {
            Category category = categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            existingProduct.setCategory(category);
        }

        if (productDto.getDiscount() != null) {
            Discount discount = discountRepository.findById(productDto.getDiscount().getId())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));
            existingProduct.setDiscount(discount);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    private ProductDTO mapToDTO(Product product) {
        CategoryDTO categoryDTO = product.getCategory() != null ? new CategoryDTO(
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getImageHash()
        ) : null;

        DiscountDTO discountDTO = product.getDiscount() != null ? new DiscountDTO(
            product.getDiscount().getId(),
            product.getDiscount().getPercentage(),
            product.getDiscount().getProducts().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList())
        ) : null;

        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getImageHash(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.isAvailable(),
            categoryDTO,
            discountDTO
        );
    }
}
