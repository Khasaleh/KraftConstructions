package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderResponseDto;

import java.io.IOException;
import java.util.List;

public interface TestimonialSliderService {
    TestimonialSliderResponseDto saveOrUpdateSliderImage(TestimonialSliderRequestDto sliderImageDTO) throws IOException;
    TestimonialSliderResponseDto uploadMultipleImages(TestimonialSliderRequestDto requestDTO) throws IOException;
    public TestimonialSliderResponseDto getSliderImageById(Long id);
    List<TestimonialSliderResponseDto> getAllSliderImages();
    void deleteSliderImageById(Long id);
}
