package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SliderService {


    SliderDto addSlider(MultipartFile[] images) throws IOException;
    List<SliderDto> getAllSliders();
    void deleteSlider(Long sliderId);
    SliderDto updateSliderImages(Long id, MultipartFile[] images) throws IOException;
}


