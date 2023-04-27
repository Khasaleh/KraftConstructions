package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceDetailsMapper {


    public static ServiceDetailsDTO toDto(ServiceDetails serviceDetails) {
        ServiceDetailsDTO dto = new ServiceDetailsDTO();
        dto.setId(serviceDetails.getId());
        dto.setServices_id(serviceDetails.getServices().getId());
        dto.setAddPortfolio(serviceDetails.isAddPortfolio());
        dto.setDescription(serviceDetails.getDescription());
        dto.setAfterImageUrl(serviceDetails.getAfterImageUrl());
        dto.setBeforeImageUrl(serviceDetails.getBeforeImageUrl());
        dto.setUpdateDate(serviceDetails.getUpdateDate());
        dto.setAuthor(serviceDetails.getAuthor());
        return dto;
    }




    public static ServiceDetails toEntity(ServiceDetailsDTO dto,Services services) {
        ServiceDetails entity = new ServiceDetails();
        entity.setId(dto.getId());
        entity.setAddPortfolio(dto.isAddPortfolio());
        entity.setDescription(dto.getDescription());
        entity.setAfterImageUrl(dto.getAfterImageUrl());
        entity.setBeforeImageUrl(dto.getBeforeImageUrl());
        entity.setUpdateDate(dto.getUpdateDate());
        services.setId(dto.getServices_id());
        entity.setServices(services);
        entity.setAuthor(dto.getAuthor());
        return entity;
    }


}