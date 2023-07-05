package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.Review;
import com.bezkoder.spring.jpa.h2.dto.ReviewDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    public ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setYourReview(review.getYourReview());
        dto.setDescription(review.getDescription());
        dto.setWorkExperience(review.getWorkExperience());
        dto.setYourName(review.getYourName());
        dto.setYourEmail(review.getYourEmail());
        dto.setCreatedDate(review.getCreatedDate());
        dto.setApprovalStatus(review.getApprovalStatus());
        return dto;
    }

    public Review toEntity(ReviewDto dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setYourReview(dto.getYourReview());
        review.setDescription(dto.getDescription());
        review.setWorkExperience(dto.getWorkExperience());
        review.setYourName(dto.getYourName());
        review.setYourEmail(dto.getYourEmail());
        review.setCreatedDate(LocalDateTime.now().toString());
        review.setApprovalStatus(dto.getApprovalStatus());
        return review;
    }

    public Review toReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setYourReview(reviewDto.getYourReview());
        review.setDescription(reviewDto.getDescription());
        review.setWorkExperience(reviewDto.getWorkExperience());
        review.setYourName(reviewDto.getYourName());
        review.setYourEmail(reviewDto.getYourEmail());
        review.setCreatedDate(LocalDateTime.now().toString());
        review.setApprovalStatus(reviewDto.getApprovalStatus());
        return review;
    }

}

