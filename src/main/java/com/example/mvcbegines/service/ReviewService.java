package com.example.mvcbegines.service;

import com.example.mvcbegines.model.Review;
import com.example.mvcbegines.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviews(){
        List<Review> reviewList = repository.findAll();
        return reviewList;
    }

    public List<Review> getReviewsByProductId(Long productId){
        List<Review> reviewListByProductId = repository.findAllByProductId(productId);
        return reviewListByProductId;
    }
}
