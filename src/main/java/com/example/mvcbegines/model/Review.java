package com.example.mvcbegines.model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public class Review {
    Long id;
    Long productId;
    String authorName;
    String content;
    @Min(value = 1, message = "Рейтинг не может быть меньше 1")
    @Max(value = 5, message = "Рейтинг не может быть больше 5")
    int rating;
    LocalDateTime createdAt;

    public Review() {
    }

    public Review(Long productId, String authorName, String content, int rating) {
        this.productId = productId;
        this.authorName = authorName;
        this.content = content;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
