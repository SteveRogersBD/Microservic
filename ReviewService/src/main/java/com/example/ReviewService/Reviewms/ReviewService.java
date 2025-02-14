package com.example.ReviewService.Reviewms;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsById(Long id);

    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long reviewId);

    boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);
}
