package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.EstimateRequest;
import com.bezkoder.spring.jpa.h2.dto.EstimateRequestDto;
import com.bezkoder.spring.jpa.h2.mapper.EstimateMapper;
import com.bezkoder.spring.jpa.h2.repository.RequestEstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RequestEstimateServiceImpl implements RequestEstimateService{
    @Autowired
    private RequestEstimateRepository requestEstimateRepository;
    @Autowired
    private EstimateMapper estimateMapper;
    public EstimateRequest getRequestById(Long id) {
        Optional<EstimateRequest> estimateRequest = requestEstimateRepository.findById(id);
        return estimateRequest.orElse(null);
    }
    @Override
    public List<EstimateRequest> getallrequest() {
       List<EstimateRequest> estimateRequest = requestEstimateRepository.findAll();
        return estimateRequest;
    }
    @Override
    public EstimateRequest saveRequest(EstimateRequestDto estimateRequestDto){
        EstimateRequest estimateRequest = new EstimateRequest();
        estimateRequest.setFirstName(estimateRequestDto.getFirstName());
        estimateRequest.setLastName(estimateRequestDto.getLastName());
        estimateRequest.setEmail(estimateRequestDto.getEmail());
        estimateRequest.setPhoneNumber(estimateRequestDto.getPhoneNumber());
        estimateRequest.setAddress(estimateRequestDto.getAddress());
        estimateRequest.setCity(estimateRequestDto.getCity());
        estimateRequest.setState(estimateRequestDto.getState());
        estimateRequest.setZip(estimateRequestDto.getZip());
        estimateRequest.setRequestedServices(estimateRequestDto.getRequestedServices());
        estimateRequest.setBudget(estimateRequestDto.getBudget());
        estimateRequest.setProjectDescription(estimateRequestDto.getProjectDescription());
        estimateRequest.setAboutUs(estimateRequestDto.getAboutUs());
        return requestEstimateRepository.save(estimateRequest);
    }
    @Override
    public void deleteRequestById(Long id) {
        requestEstimateRepository.deleteById(id);
    }
}
