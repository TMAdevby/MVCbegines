package com.example.mvcbegines.repository;

import com.example.mvcbegines.model.Review;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReviewRepository {
    private final ConcurrentHashMap<Long, Review> rewievMap = new ConcurrentHashMap<>();
    // Счетчик для генерации ID (как в ТЗ)
    private final AtomicLong idCounter = new AtomicLong(6); // Начинаем с 6, так как у нас уже 5 отзывов

    @PostConstruct
    public void init() {
        // Заполняем тестовыми данными
        Review review1 = new Review(1L, "Алексей", "Отличный товар! Качество супер.", 5);
        review1.setId(1L);
        review1.setCreatedAt(LocalDateTime.now().minusDays(5));
        rewievMap.put(1L, review1);

        Review review2 = new Review(1L, "Мария", "Хороший товар, но доставка долгая.", 4);
        review2.setId(2L);
        review2.setCreatedAt(LocalDateTime.now().minusDays(4));
        rewievMap.put(2L, review2);

        Review review3 = new Review(1L, "Дмитрий", "Среднее качество, ожидал большего.", 3);
        review3.setId(3L);
        review3.setCreatedAt(LocalDateTime.now().minusDays(3));
        rewievMap.put(3L, review3);

        Review review4 = new Review(2L, "Елена", "Ужасный товар, сломался через день!", 1);
        review4.setId(4L);
        review4.setCreatedAt(LocalDateTime.now().minusDays(2));
        rewievMap.put(4L, review4);

        Review review5 = new Review(3L, "Сергей", "Рекомендую! Все работает отлично.", 5);
        review5.setId(5L);
        review5.setCreatedAt(LocalDateTime.now().minusDays(1));
        rewievMap.put(5L, review5);
    }
    // Получение следующего ID для новых отзывов
    public long getNextId() {
        return idCounter.getAndIncrement();
    }

    public List<Review> findAll(){
        return  new ArrayList<Review> (rewievMap.values());
    }

    public Optional<Review> findById(Long id){
        return Optional.ofNullable(rewievMap.get(id));
    }

    public List<Review> findAllByProductId(Long productId){
        ArrayList<Review> listReview = new ArrayList<>();
        for(Map.Entry<Long,Review> item : rewievMap.entrySet()){
            if(item.getValue().getProductId().equals(productId)){
                listReview.add(item.getValue());
            }
        }
        return listReview;
    }

    public Review save(Review review){
        if(review.getId() == 0 || review.getId().equals(null)){
            review.setId(idCounter.get());
            getNextId();
        }
        rewievMap.put(review.getId(),review);
        return review;
    }

    public void deleteById(Long id){

    }



}