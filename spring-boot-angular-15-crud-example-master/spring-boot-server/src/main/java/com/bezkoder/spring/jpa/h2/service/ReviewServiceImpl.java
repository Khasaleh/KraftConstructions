package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ReviewDto;
import com.bezkoder.spring.jpa.h2.Entity.ApprovalStatus;
import com.bezkoder.spring.jpa.h2.Entity.Review;
import com.bezkoder.spring.jpa.h2.mapper.ReviewMapper;
import com.bezkoder.spring.jpa.h2.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public ReviewDto getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(reviewMapper::toDto).orElse(null);
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toReview(reviewDto);
        Review createdReview = reviewRepository.save(review);
        return reviewMapper.toDto(createdReview);
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.saveReview(review);
    }


    @Override
    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void approveReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setApprovalStatus(ApprovalStatus.APPROVED);
            reviewRepository.save(review);
        }
    }

    @Override
    public void hideApprovedReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setApprovalStatus(ApprovalStatus.HIDDEN);
            reviewRepository.save(review);
        }
    }

    @Override
    public List<Review> getApprovedReviews() {
        return reviewRepository.findByApprovalStatus(ApprovalStatus.APPROVED);
    }

}





