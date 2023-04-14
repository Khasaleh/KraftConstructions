package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ReviewDto;
import com.bezkoder.spring.jpa.h2.entity.Review;
import com.bezkoder.spring.jpa.h2.mapper.ReviewMapper;
import com.bezkoder.spring.jpa.h2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper reviewMapper;

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

//        @PostMapping
//    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
//        return reviewService.createReview(reviewDto);
//    }
    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto); // convert ReviewDto to Review
        Review createdReview = reviewService.createReview(review); // create the review
        return reviewMapper.toDto(createdReview); // convert the resulting Review to ReviewDto and return
    }
    @DeleteMapping("/{id}")
    public void deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReviewById(id); // delete the review by id
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveReview(@PathVariable Long id) {
        reviewService.approveReview(id);
        return ResponseEntity.ok().build();
    }
}



