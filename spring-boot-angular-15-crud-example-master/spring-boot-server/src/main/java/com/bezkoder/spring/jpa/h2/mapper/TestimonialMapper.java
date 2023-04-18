package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.dto.TestimonialDto;
import com.bezkoder.spring.jpa.h2.Entity.Testimonial;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {
    public static TestimonialDto toResponse(Testimonial testimonial) {
        TestimonialDto response = new TestimonialDto();
        response.setId(testimonial.getId());
        response.setPage(testimonial.getPage());
        response.setImageUrl(testimonial.getImageUrl());
        return response;
    }

    public static Testimonial toEntity(TestimonialDto dto) {
        Testimonial entity = new Testimonial();
        entity.setId(dto.getId());
        entity.setPage(dto.getPage());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
}

