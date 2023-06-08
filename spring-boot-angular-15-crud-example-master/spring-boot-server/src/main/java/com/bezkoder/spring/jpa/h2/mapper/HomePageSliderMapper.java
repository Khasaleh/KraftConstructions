package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.HomePageSlider;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HomePageSliderMapper {
    public HomePageSlider toEntity(HomePageSliderRequestDto requestDTO) {
        HomePageSlider entity = new HomePageSlider();
        entity.setId(requestDTO.getId());
        return entity;
    }

    public HomePageSliderResponseDto toResponseDTO(HomePageSlider entity) {
        HomePageSliderResponseDto responseDTO = new HomePageSliderResponseDto();
        responseDTO.setId(entity.getId());
        responseDTO.setImageUrl(entity.getImageUrl());
        return responseDTO;
    }
}