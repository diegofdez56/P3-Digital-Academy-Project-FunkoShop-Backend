package org.factoriaf5.digital_academy.funko_shop.product;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryRepository;
import org.factoriaf5.digital_academy.funko_shop.category.category_exceptions.CategoryNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.discount.Discount;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountDTO;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountRepository;
import org.factoriaf5.digital_academy.funko_shop.discount.discount_exceptions.DiscountNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.product.product_exceptions.ProductNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.discountRepository = discountRepository;
    }

    public ProductDTO createProduct(ProductDTO productDto, Long categoryId, Long discountId) {
        Category category = getCategoryById(categoryId);
        Discount discount = discountId != null ? getDiscountById(discountId) : null;

        Product product = mapToEntity(productDto);
        product.setCategory(category);
        product.setDiscount(discount);

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        
        return productRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    public Page<ProductDTO> getProductsByCategory(Long categoryId, Pageable pageable) {
        Category category = getCategoryById(categoryId);
        
        return productRepository.findByCategory(category, pageable)
                .map(this::mapToDTO);
    }

    public Page<ProductDTO> searchProductsByKeyword(String keyword, Pageable pageable) {
       
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(this::mapToDTO);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        updateProductFields(existingProduct, productDto);

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
    }

    private Discount getDiscountById(Long discountId) {
        return discountRepository.findById(discountId)
                .orElseThrow(() -> new DiscountNotFoundException("Discount not found with id: " + discountId));
    }

    

    private void updateProductFields(Product product, ProductDTO productDto) {
        product.setName(productDto.getName());
        product.setImageHash(productDto.getImageHash());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setAvailable(productDto.isAvailable());

        if (productDto.getCategory() != null) {
            product.setCategory(getCategoryById(productDto.getCategory().getId()));
        }

        if (productDto.getDiscount() != null) {
            product.setDiscount(getDiscountById(productDto.getDiscount().getId()));
        }
    }

    private Product mapToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setImageHash(dto.getImageHash());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setAvailable(dto.isAvailable());
        return product;
    }

    private ProductDTO mapToDTO(Product product) {
        CategoryDTO categoryDTO = product.getCategory() != null ? new CategoryDTO(
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getImageHash()) : null;

        DiscountDTO discountDTO = product.getDiscount() != null ? new DiscountDTO(
                product.getDiscount().getId(),
                product.getDiscount().getPercentage(),
                product.getDiscount().isActive(),
                null)
                : null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImageHash(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.isAvailable(),
                categoryDTO,
                discountDTO);
    }
}