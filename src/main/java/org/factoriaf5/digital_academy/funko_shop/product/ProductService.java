package org.factoriaf5.digital_academy.funko_shop.product;

import java.util.List;
import java.util.stream.Collectors;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryRepository;
import org.factoriaf5.digital_academy.funko_shop.category.category_exceptions.CategoryNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.discount.Discount;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountDTO;
import org.factoriaf5.digital_academy.funko_shop.discount.DiscountRepository;
import org.factoriaf5.digital_academy.funko_shop.discount.discount_exceptions.DiscountNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.product.product_exceptions.ProductNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<ProductDTO> getAllProducts(int pageNum, int pageSize, String sortBy, String sortOrder) {
        PageRequest pageRequest = createPageRequest(pageNum, pageSize, sortBy, sortOrder);
        return productRepository.findAll(pageRequest).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId, int pageNum, int pageSize, String sortBy,
                                                  String sortOrder) {
        Category category = getCategoryById(categoryId);
        PageRequest pageRequest = createPageRequest(pageNum, pageSize, sortBy, sortOrder);
        return productRepository.findByCategory(category, pageRequest).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> searchProductsByKeyword(String keyword, int pageNum, int pageSize, String sortBy,
                                                    String sortOrder) {
        PageRequest pageRequest = createPageRequest(pageNum, pageSize, sortBy, sortOrder);
        return productRepository.findByNameContainingIgnoreCase(keyword, pageRequest).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
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

    private PageRequest createPageRequest(int pageNum, int pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(pageNum, pageSize, Sort.by(direction, sortBy));
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