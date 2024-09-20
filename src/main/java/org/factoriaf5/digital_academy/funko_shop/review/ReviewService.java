package org.factoriaf5.digital_academy.funko_shop.review;

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

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        OrderItem orderItem = orderItemRepository.findById(reviewDTO.getOrderItem().getId())
                .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

        User user = userRepository.findById(reviewDTO.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setOrderItem(orderItem);
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);
        return mapToDTO(savedReview);
    }

    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return mapToDTO(review);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        OrderItem orderItem = orderItemRepository.findById(reviewDTO.getOrderItem().getId())
                .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

        User user = userRepository.findById(reviewDTO.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        review.setRating(reviewDTO.getRating());
        review.setOrderItem(orderItem);
        review.setUser(user);

        Review updatedReview = reviewRepository.save(review);
        return mapToDTO(updatedReview);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        reviewRepository.delete(review);
    }

    private ReviewDTO mapToDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getRating(),
                review.getOrderItem() != null ? mapToOrderItemDTO(review.getOrderItem()) : null,
                review.getUser() != null ? new UserDTO(
                        review.getUser().getId(),
                        review.getUser().getEmail(),
                        review.getUser().getPassword(),
                        null, null, null, null, null, null) : null
        );
    }
    private OrderItemDTO mapToOrderItemDTO(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        return new OrderItemDTO(
            orderItem.getId(),
            orderItem.getQuantity(),
            null, 
            product != null ? new ProductDTO(orderItem.getProduct().getId(), orderItem.getProduct().getName(), 
                orderItem.getProduct().getImageHash(), orderItem.getProduct().getDescription(),
                orderItem.getProduct().getPrice(), orderItem.getProduct().getStock(), 
                orderItem.getProduct().isAvailable(), null, null) : null,
            null  
        );
    }
    
}
