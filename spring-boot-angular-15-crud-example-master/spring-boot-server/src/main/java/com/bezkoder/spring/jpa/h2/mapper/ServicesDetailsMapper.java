package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsResponseDTO;
import com.bezkoder.spring.jpa.h2.entity.ServiceDetails;
import org.springframework.stereotype.Component;

@Component
public class ServicesDetailsMapper {

    public ServiceDetails toEntity(ServiceDetailsRequestDTO dto) {
        ServiceDetails entity = new ServiceDetails();
        entity.setServiceName(dto.getServiceName());
        entity.setBeforeImageUrl(dto.getBeforeImageUrl());
        entity.setAfterImageUrl(dto.getAfterImageUrl());
        entity.setDescription(dto.getDescription());
        entity.setAddPortfolio(dto.getAddPortfolio());
        return entity;
    }

    public ServiceDetailsResponseDTO toDto(ServiceDetails entity) {
        ServiceDetailsResponseDTO dto = new ServiceDetailsResponseDTO();
        dto.setServiceName(entity.getServiceName());
        dto.setBeforeImageUrl(entity.getBeforeImageUrl());
        dto.setAfterImageUrl(entity.getAfterImageUrl());
        dto.setDescription(entity.getDescription());
        dto.setAddPortfolio(entity.getAddPortfolio());
        return dto;
    }


}