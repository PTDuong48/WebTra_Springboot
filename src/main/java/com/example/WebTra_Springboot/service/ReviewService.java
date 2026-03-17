package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review getReviewById(Long id);
    List<Review> getReviewsByProductId(Long productId);
    Review saveReview(Review review);
    void deleteReview(Long id);
}
