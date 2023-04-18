package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.dto.ReviewDto;
import com.bezkoder.spring.jpa.h2.Entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setYourReview(review.getYourReview());
        dto.setWorkExperience(review.getWorkExperience());
        dto.setYourName(review.getYourName());
        dto.setYourEmail(review.getYourEmail());
        dto.setApprovalStatus(review.getApprovalStatus());
        return dto;
    }

    public Review toEntity(ReviewDto dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setYourReview(dto.getYourReview());
        review.setWorkExperience(dto.getWorkExperience());
        review.setYourName(dto.getYourName());
        review.setYourEmail(dto.getYourEmail());
        review.setApprovalStatus(dto.getApprovalStatus());
        return review;
    }

    public Review toReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setYourReview(reviewDto.getYourReview());
        review.setWorkExperience(reviewDto.getWorkExperience());
        review.setYourName(reviewDto.getYourName());
        review.setYourEmail(reviewDto.getYourEmail());
        review.setApprovalStatus(reviewDto.getApprovalStatus());
        return review;
    }

}

