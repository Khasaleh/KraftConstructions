package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.TestimonialSliderImage;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TestimonialSliderMapper {
    public TestimonialSliderImage toEntity(TestimonialSliderRequestDto requestDTO) {
        TestimonialSliderImage entity = new TestimonialSliderImage();
        entity.setId(requestDTO.getId());
        return entity;
    }

    public TestimonialSliderResponseDto toResponseDTO(TestimonialSliderImage entity) {
        TestimonialSliderResponseDto responseDTO = new TestimonialSliderResponseDto();
        responseDTO.setId(entity.getId());
        responseDTO.setImageUrl(entity.getImageUrl());
        return responseDTO;
    }
}
