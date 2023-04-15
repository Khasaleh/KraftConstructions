package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ReviewDto;
import com.bezkoder.spring.jpa.h2.entity.ApprovalStatus;
import com.bezkoder.spring.jpa.h2.entity.Review;
import com.bezkoder.spring.jpa.h2.mapper.ReviewMapper;
import com.bezkoder.spring.jpa.h2.repository.ReviewRepository;
import com.bezkoder.spring.jpa.h2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        Review createdReview = reviewService.createReview(review);
        return reviewMapper.toDto(createdReview);
    }

    @DeleteMapping("/{id}")
    public void deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReviewById(id); // delete the review by id
    }


    @PutMapping("/approve/{id}")
    public ResponseEntity<ReviewDto> approveReview(@PathVariable Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setApprovalStatus(ApprovalStatus.APPROVED);
            Review updatedReview = reviewRepository.save(review);
            return ResponseEntity.ok(reviewMapper.toDto(updatedReview));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity<ReviewDto> hideReview(@PathVariable Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            if (review.getApprovalStatus() == ApprovalStatus.APPROVED) {
                review.setApprovalStatus(ApprovalStatus.HIDDEN);
                Review updatedReview = reviewRepository.save(review);
                return ResponseEntity.ok(reviewMapper.toDto(updatedReview));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<ReviewDto>> getApprovedReviews() {
        List<Review> reviews = reviewRepository.findByApprovalStatus(ApprovalStatus.APPROVED);
        List<ReviewDto> reviewDtos = reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

}



