package com.example.ReviewService.Reviewms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public List<Review> getReviewsById(Long id) {
        return reviewRepo.findByCompanyId(id);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null) {
            review.setCompanyId(companyId);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
        if(reviewId == null) {return false;}
        Review oldReview = reviewRepo.findById(reviewId).orElse(null);
        if (oldReview != null) {
            oldReview.setCompanyId(review.getCompanyId());
            oldReview.setTitle(review.getTitle());
            oldReview.setDescription(review.getDescription());
            oldReview.setRating(review.getRating());
            reviewRepo.save(oldReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review oldReview = reviewRepo.findById(reviewId).orElse(null);
        if (oldReview != null) {
            reviewRepo.delete(oldReview);
            return true;
        }
        return false;
    }
}