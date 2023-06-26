package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.DashBoardStatistics;
import com.bezkoder.spring.jpa.h2.repository.CareersApplicationRepository;
import com.bezkoder.spring.jpa.h2.repository.RequestEstimateRepository;
import com.bezkoder.spring.jpa.h2.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardServiceImpl implements DashBoardService{
    @Autowired
    private RequestEstimateRepository requestEstimateRepository;
    @Autowired
    private CareersApplicationRepository careersApplicationRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public DashBoardStatistics getHomepageStatistics() {
        long testimonialCount = reviewRepository.count();
        long careerApplicationCount = careersApplicationRepository.count();
        long requestEstimateCount = requestEstimateRepository.count();

        return new DashBoardStatistics(testimonialCount, careerApplicationCount, requestEstimateCount);
    }
}
