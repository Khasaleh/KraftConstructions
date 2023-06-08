package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.HomePageSlider;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HomePageSliderMapper {
    public HomePageSliderResponseDto toDTO(HomePageSlider entity) {
        HomePageSliderResponseDto dto = new HomePageSliderResponseDto();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getName());
        return dto;
    }

    public HomePageSlider toEntity(HomePageSliderResponseDto dto) {
        HomePageSlider entity = new HomePageSlider();
        entity.setId(dto.getId());
        entity.setName(dto.getImageUrl());
        return entity;
    }
    public void updateEntity(HomePageSlider entity, HomePageSliderResponseDto dto) {
        entity.setName(dto.getImageUrl());
    }
}