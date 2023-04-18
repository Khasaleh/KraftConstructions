package com.bezkoder.spring.jpa.h2.mapper;

//public class SliderMapper {
//    public SliderDto toDto(Slider slider) {
//        SliderDto dto = new SliderDto();
//        dto.setId(slider.getId());
//        dto.setImageUrls(slider.getImageUrls());
//        return dto;
//    }
//
//    public Slider toEntity(SliderDto dto) {
//        Slider slider = new Slider();
//        slider.setId(dto.getId());
//        slider.setImageUrls(dto.getImageUrls());
//        return slider;
//    }

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.Entity.Slider;
import org.springframework.stereotype.Component;

@Component
public class SliderMapper {

    public SliderDto toDto(Slider slider) {
        SliderDto dto = new SliderDto();
        dto.setId(slider.getId());
        dto.setImageUrls(slider.getImageUrls());
        return dto;
    }

    public Slider toEntity(SliderDto dto) {
        Slider slider = new Slider();
        slider.setId(dto.getId());
        slider.setImageUrls(dto.getImageUrls());
        return slider;
    }

}

