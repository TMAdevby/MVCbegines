package com.example.mvcbegines.service;

import com.example.mvcbegines.exception.ReviewNotFoundException;
import com.example.mvcbegines.model.Review;
import com.example.mvcbegines.repository.ReviewRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviews() {
        List<Review> reviewList = repository.findAll();
        return reviewList;
    }

    public List<Review> getReviewsByProductId(Long productId) {
        List<Review> reviewListByProductId = repository.findAllByProductId(productId);
        return reviewListByProductId;
    }

    public Review getReviewById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("There is no review with this id " + id));
    }

    public Review createReview(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating can not be less then 1 and bigger then 5");
        }
        review.setCreatedAt(LocalDateTime.now());
        repository.save(review);
        return review;
    }

    public Review updateReview(Long id, Review reviewDetails) {
        if (reviewDetails.getRating() < 1 || reviewDetails.getRating() > 5) {
            throw new IllegalArgumentException("Rating can not be less than 1 and bigger than 5");
        }
        Review review = repository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("There is no review with this id " + id));
        review.setAuthorName(reviewDetails.getAuthorName());
        review.setContent(reviewDetails.getContent());
        review.setRating(reviewDetails.getRating());
        repository.save(review);
        return review;
    }

    public void deleteReview(Long id) {
        repository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("There is no review with this id " + id));
        repository.deleteById(id);
    }

    public Double getAverageRating(Long productId) {
        List<Review> list = repository.findAllByProductId(productId);
        if (list.isEmpty()) {
            return 0.0;
        }
        int ratingSum = 0;
        for (Review review : list) {
            ratingSum += review.getRating();
        }
        double avRating = (double) ratingSum / list.size();
        return avRating;
    }
}
