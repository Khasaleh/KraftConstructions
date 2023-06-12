package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.EstimateRequest;
import com.bezkoder.spring.jpa.h2.dto.EstimateRequestDto;
import com.bezkoder.spring.jpa.h2.dto.EstimateResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.EstimateMapper;
import com.bezkoder.spring.jpa.h2.service.RequestEstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/estimate-request")
public class RequestEstimateController {
    @Autowired
    private RequestEstimateService requestEstimateService;
    @Autowired
    private EstimateMapper estimateMapper;
    @GetMapping("/getrequests/{id}")
    public ResponseEntity<EstimateResponseDto> getEstimateRequestById(@PathVariable("id") Long id) {
        EstimateRequest estimateRequest = requestEstimateService.getRequestById(id);
        if (estimateRequest != null) {
            EstimateResponseDto estimateResponseDto = estimateMapper.toDto(estimateRequest);
            return ResponseEntity.ok(estimateResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getrequests")
    public ResponseEntity<List<EstimateRequest>> getAllEstimateRequests() {
        List<EstimateRequest> estimateRequests = requestEstimateService.getallrequest();
        return ResponseEntity.ok(estimateRequests);
    }

    @PostMapping("/saverequest")
    public ResponseEntity<?> saveEstimateRequest(@RequestBody EstimateRequestDto estimateRequestDto) {
        EstimateRequest estimateRequest = requestEstimateService.saveRequest(estimateRequestDto);
        return ResponseEntity.ok("Submitted Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEstimateRequestById(@PathVariable("id") Long id) {
        requestEstimateService.deleteRequestById(id);
        return ResponseEntity.ok("Request Deleted Successfully");
    }
}
