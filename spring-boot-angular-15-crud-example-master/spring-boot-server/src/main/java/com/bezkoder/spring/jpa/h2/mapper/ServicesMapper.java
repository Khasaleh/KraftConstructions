package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import org.springframework.stereotype.Component;



@Component
public class ServicesMapper {

    public Services toEntity(ServicesRequestDTO dto) {
        Services entity = new Services();
        entity.setServiceName(dto.getServiceName());
        entity.setPageName(dto.getPageName());
        entity.setActive(dto.isActive());
        return entity;
    }

    public static ServicesResponseDTO toDto(Services entity) {
        ServicesResponseDTO dto = new ServicesResponseDTO();
        dto.setServiceName(entity.getServiceName());
        dto.setPageName(entity.getPageName());
        dto.setActive(entity.isActive());
        return dto;
    }

    public static Services toService(ServicesResponseDTO dto) {
        Services entity = new Services();
        entity.setServiceName(dto.getServiceName());
        entity.setPageName(dto.getPageName());
        entity.setActive(dto.isActive());
        return entity;
    }




}
