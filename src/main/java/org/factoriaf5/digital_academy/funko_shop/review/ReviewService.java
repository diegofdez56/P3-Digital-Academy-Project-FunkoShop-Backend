package org.factoriaf5.digital_academy.funko_shop.review;

import org.factoriaf5.digital_academy.funko_shop.category.Category;
import org.factoriaf5.digital_academy.funko_shop.category.CategoryDTO;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItem;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItemDTO;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItemRepository;
import org.factoriaf5.digital_academy.funko_shop.product.Product;
import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;
import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;
import org.factoriaf5.digital_academy.funko_shop.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void addReview(ReviewDTO reviewDTO, User user) {

        OrderItem orderItem = orderItemRepository.findById(reviewDTO.getOrderItem())
                .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

        Review isReview = reviewRepository.findByOrderItemAndUser(orderItem, user);

        if (isReview == null) {
            Review review = new Review();
            review.setRating(reviewDTO.getRating());
            review.setOrderItem(orderItem);
            review.setUser(user);

            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Review already exists");
        }
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO, User user) {

        OrderItem orderItem = orderItemRepository.findById(reviewDTO.getOrderItem()).get();
        Review review = mapToEntity(reviewDTO, orderItem, user);
        Review updatedReview = reviewRepository.save(review);
        return mapToDTO(updatedReview);
    }

    public ReviewDTO getReviewByOrderItemIdAndUser(Long orderItemId, User user) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

        Review review = reviewRepository.findByOrderItemAndUser(orderItem, user);

        if (review == null) {
            return null;
        }

        return mapToDTO(review);
    }

    private ReviewDTO mapToDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getRating(),
                review.getOrderItem().getId());
    }

    private Review mapToEntity(ReviewDTO reviewDTO, OrderItem orderItem, User user) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setRating(reviewDTO.getRating());
        review.setOrderItem(orderItem);
        review.setUser(user);
        return review;
    }

    /*
     * private OrderItemDTO mapToOrderItemDTO(OrderItem orderItem) {
     * Product product = orderItem.getProduct();
     * 
     * Category category = product.getCategory();
     * CategoryDTO categoryDTO = null;
     * if (category != null) {
     * categoryDTO = new CategoryDTO(
     * category.getId(),
     * category.getName(),
     * category.getImageHash(),
     * category.isHighlights());
     * }
     * 
     * List<Review> reviews = Optional.ofNullable(product.getOrderItems())
     * .orElse(Collections.emptyList())
     * .stream()
     * .map(OrderItem::getReview)
     * .filter(Objects::nonNull)
     * .collect(Collectors.toList());
     * 
     * int totalReviews = reviews.size();
     * double averageRating = totalReviews > 0 ? reviews.stream()
     * .mapToInt(Review::getRating)
     * .average()
     * .orElse(0.0) : 0.0;
     * 
     * ProductDTO productDTO = new ProductDTO(
     * product.getId(),
     * product.getName(),
     * Optional.ofNullable(product.getImageHash()),
     * Optional.ofNullable(product.getImageHash2()),
     * product.getDescription(),
     * product.getPrice(),
     * product.getPrice(),
     * product.getStock(),
     * product.getCreatedAt(),
     * categoryDTO,
     * product.getDiscount(),
     * totalReviews,
     * averageRating);
     * 
     * return new OrderItemDTO(
     * orderItem.getId(),
     * orderItem.getQuantity(),
     * null,
     * productDTO);
     * }
     */

    /*
     * public ReviewDTO getReviewById(Long id) {
     * Review review = reviewRepository.findById(id)
     * .orElseThrow(() -> new IllegalArgumentException("Review not found"));
     * return mapToDTO(review);
     * }
     * 
     * public List<ReviewDTO> getAllReviews() {
     * return reviewRepository.findAll().stream()
     * .map(this::mapToDTO)
     * .toList();
     * }
     */

    /*
     * public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
     * Review review = reviewRepository.findById(id)
     * .orElseThrow(() -> new IllegalArgumentException("Review not found"));
     * 
     * OrderItem orderItem =
     * orderItemRepository.findById(reviewDTO.getOrderItem().getId())
     * .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));
     * 
     * User user = userRepository.findById(reviewDTO.getUser().getId())
     * .orElseThrow(() -> new IllegalArgumentException("User not found"));
     * 
     * review.setRating(reviewDTO.getRating());
     * review.setOrderItem(orderItem);
     * review.setUser(user);
     * 
     * Review updatedReview = reviewRepository.save(review);
     * return mapToDTO(updatedReview);
     * }
     * 
     * public void deleteReview(Long id) {
     * Review review = reviewRepository.findById(id)
     * .orElseThrow(() -> new IllegalArgumentException("Review not found"));
     * reviewRepository.delete(review);
     * }
     * 
     * 
     * 
     * 
     */
}
