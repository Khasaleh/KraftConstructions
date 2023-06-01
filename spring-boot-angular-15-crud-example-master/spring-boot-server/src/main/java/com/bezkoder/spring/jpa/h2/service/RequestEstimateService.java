package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.EstimateRequest;
import com.bezkoder.spring.jpa.h2.dto.EstimateRequestDto;

import java.util.List;

public interface RequestEstimateService {
    EstimateRequest getRequestById(Long id);
    List<EstimateRequest> getallrequest();
    EstimateRequest saveRequest(EstimateRequestDto estimateRequestDto);
    void deleteRequestById(Long id);
}
