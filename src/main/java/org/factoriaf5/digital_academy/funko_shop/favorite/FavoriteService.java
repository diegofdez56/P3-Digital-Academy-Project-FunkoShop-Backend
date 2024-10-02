package org.factoriaf5.digital_academy.funko_shop.favorite;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.factoriaf5.digital_academy.funko_shop.product.Product;
import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;
import org.factoriaf5.digital_academy.funko_shop.product.ProductRepository;
import org.factoriaf5.digital_academy.funko_shop.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<FavoriteDTO> addProductToFavorite(Long userId, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();

        Favorite favorite = new Favorite();
        favorite.setUser(new User(userId, null, null, null, null, null, null, null, null, null));
        favorite.setProduct(product);

        favoriteRepository.save(favorite);

        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(this::convertToFavoriteDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImageHash(),
                product.getDescription(),
                product.getPrice(),
                calculateDiscountedPrice(product.getPrice(), product.getDiscount()), 
                product.getStock(),
                product.getCreatedAt(),
                convertToCategoryDTO(product.getCategory()), 
                product.getDiscount());
    }

    private float calculateDiscountedPrice(float price, int discount) {
        if (discount > 0) {
            return price - (price * discount / 100);
        }
        return price;
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getImageHash(),
                category.isHighlights());
    }

    private FavoriteDTO convertToFavoriteDTO(Favorite favorite) {
        return new FavoriteDTO(favorite.getId(), convertToProductDTO(favorite.getProduct()), favorite.getUser().getId());
    }

    /*
     * private Product convertToProductDTO(ProductDTO productDTO) {
     * return new Product(
     * productDTO.getId(),
     * productDTO.getName(),
     * productDTO.getImageHash(),
     * productDTO.getDescription(),
     * productDTO.getPrice(),
     * productDTO.getStock(),
     * convertToCategory(productDTO.getCategory()),
     * productDTO.getDiscount(),
     * productDTO.getCreatedAt(),
     * null
     * );
     * }
     * 
     * private Category convertToCategory(CategoryDTO categoryDTO) {
     * return new Category(
     * categoryDTO.getId(),
     * categoryDTO.getName(),
     * categoryDTO.getImageHash(),
     * categoryDTO.isHighlights(),
     * null
     * );
     * }
     */

    // public void removeProductFromFavorite(Long userId, Long productId) {
    // Favorite favorite = favoriteRepository.findByUserId(userId);
    // Product product = productRepository.findById(productId).orElseThrow();
    // favorite.getProducts().remove(product);
    // favoriteRepository.save(favorite);
    // }

    // public List<FavoriteDTO> getFavoriteByUserId(Long userId) {
    // Favorite favorite = favoriteRepository.findByUserId(userId);
    // // Convertimos la lista de productos a FavoriteDTO
    // return favorite.getProducts().stream()
    // .map(this::mapProductToFavoriteDTO)
    // .collect(Collectors.toList());
    // }

    // private Favorite createNewFavorite(Long userId) {
    // Favorite newFavorite = new Favorite();
    // newFavorite.setUserId(userId);
    // return favoriteRepository.save(newFavorite);
    // }

    // // Mapeo manual de Product a FavoriteDTO
    // private FavoriteDTO mapProductToFavoriteDTO(Product product) {
    // FavoriteDTO favoriteDTO = new FavoriteDTO();
    // favoriteDTO.setId(product.getId());

    // return favoriteDTO;
    // }

    // private FavoriteDTO mapFavoriteToDTO(Favorite favorite) {
    // return FavoriteDTO.builder()
    // .id(favorite.getId())
    // .product(favorite.getProduct())
    // .user(favorite.getUser().getId())
    // .build();
    // }

    // private Favorite mapToEntity(FavoriteDTO favoriteDTO, Product product, User
    // user) {
    // return Favorite.builder()
    // .id(favoriteDTO.getId())
    // .product(product.getId())
    // .user(user)
    // .build();
    // }
}
