package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ReviewDto;
import com.bezkoder.spring.jpa.h2.Entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
    ReviewDto createReview(ReviewDto reviewDto);
    Review createReview(Review review);
    void deleteReviewById(Long id);
    void approveReview(Long id);
    void hideApprovedReview(Long id);
    List<Review> getApprovedReviews();


}

