package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.EstimateRequest;
import com.bezkoder.spring.jpa.h2.dto.EstimateResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EstimateMapper {
    public EstimateResponseDto toDto(EstimateRequest entity) {
        EstimateResponseDto dto = new EstimateResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZip(entity.getZip());
        dto.setRequestedServices(entity.getRequestedServices());
        dto.setBudget(entity.getBudget());
        dto.setProjectDescription(entity.getProjectDescription());
        dto.setAboutUs(entity.getAboutUs());
        return dto;
    }
}
