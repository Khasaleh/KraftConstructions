package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.TestimonialsHomePage;
import com.bezkoder.spring.jpa.h2.dto.TestimonialHomePageRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.TestimonialHomePageResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class TestimonialsHomePageMapper {
    public TestimonialsHomePage toEntity(TestimonialHomePageRequestDTO dto) {
        TestimonialsHomePage entity = new TestimonialsHomePage();
        entity.setHeading(dto.getHeading());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        return entity;
    }

    public List<TestimonialsHomePage> toEntities(List<TestimonialHomePageRequestDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public TestimonialHomePageResponseDTO toResponseDTO(TestimonialsHomePage entity) {
        TestimonialHomePageResponseDTO dto = new TestimonialHomePageResponseDTO();
        dto.setId(entity.getId());
        dto.setHeading(entity.getHeading());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        return dto;
    }

    public List<TestimonialHomePageResponseDTO> toResponseDTOs(List<TestimonialsHomePage> entities) {
        return entities.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

}
