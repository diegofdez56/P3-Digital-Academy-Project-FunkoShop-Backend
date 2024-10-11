package org.factoriaf5.digital_academy.funko_shop.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDTO> addReview(Principal connectedUser, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.addReview(reviewDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO reviewDTO = reviewService.getReviewById(id);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDTO> updateReview(Principal connectedUser, @PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteReview(Principal connectedUser, @PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
