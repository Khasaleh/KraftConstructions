package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.HomePageSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;

import java.io.IOException;

public interface HomePageSliderService {
    HomePageSliderResponseDto saveOrUpdateSliderImage(HomePageSliderRequestDto sliderImageDTO) throws IOException;

    public HomePageSliderResponseDto getSliderImageById(Long id);
}
