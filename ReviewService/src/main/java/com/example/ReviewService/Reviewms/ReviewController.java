package com.example.ReviewService.Reviewms;

import com.example.ReviewService.Reviewms.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public ResponseEntity<List<Review>>getAllReviews(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.getReviewsById(companyId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String>createReview(@RequestParam Long companyId,
                                              @RequestBody Review review)
    {
        boolean flag = reviewService.addReview(companyId,review);
        if(flag){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review created", HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review>getReviewById(@PathVariable Long reviewId)
    {
        Review review = reviewService.getReviewById(reviewId);
        if(review != null) return new ResponseEntity<>(review, HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String>updateReview(@RequestParam Long reviewId,
                                              @RequestBody Review review)
    {
        boolean flag = reviewService.updateReview(reviewId,review);
        if(flag) return new ResponseEntity<>("Review updated", HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String>deleteReview(@RequestParam Long reviewId)
    {
        boolean flag = reviewService.deleteReview(reviewId);
        if(flag) return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/averageRating")
    public double getAverageRating(@RequestParam Long companyId)
    {
        List<Review> reviews = reviewService.getReviewsById(companyId);
        return reviews.stream().mapToDouble(Review::getRating).average().
                orElse(0.0);
    }
}
