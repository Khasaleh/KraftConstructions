package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;

import java.util.List;

public interface HomePageSliderService {
    List<HomePageSliderResponseDto> getAllSliderImages();

    HomePageSliderResponseDto createOrUpdateSliderImage(HomePageSliderResponseDto dto);

    void deleteSliderImage(Long id);
}
