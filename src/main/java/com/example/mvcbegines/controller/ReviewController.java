package com.example.mvcbegines.controller;

import com.example.mvcbegines.model.Review;
import com.example.mvcbegines.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> getAllReviews(){
        return service.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReviewsById(@PathVariable long id){
        return service.getReviewById(id);
    }

    @GetMapping("/api/reviews/product/{productId}")
    public List<Review> getReviewsByProductId(@PathVariable Long productId) {
        return service.getReviewsByProductId(productId);
    }

    @GetMapping("/api/reviews/product/{productId}/average-rating")
    public Double getAverageRating(@PathVariable Long productId){
        return service.getAverageRating(productId);
    }

    @PostMapping("/api/reviews")
    public Review createReview(@RequestBody Review review) {
        return service.createReview(review);
    }

    @PutMapping("/api/reviews/{id}")
    public Review updateReview(@PathVariable Long id,@RequestBody Review reviewDetails) {
        return service.updateReview(id,reviewDetails);
    }
}
