package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.Slider;
import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SliderMapper {

    public SliderDto toDto(Slider slider) {
        if (slider == null) {
            return null;
        }

        SliderDto dto = new SliderDto();
        dto.setId(slider.getId());
        dto.setImageUrls(new ArrayList<>(slider.getImageUrls()));

        return dto;
    }

    public List<SliderDto> toDtos(List<Slider> sliders) {
        List<SliderDto> dtos = new ArrayList<>();
        for (Slider slider : sliders) {
            dtos.add(toDto(slider));
        }
        return dtos;
    }

    public Slider toEntity(SliderDto dto) {
        if (dto == null) {
            return null;
        }

        Slider entity = new Slider();
        entity.setId(dto.getId());
        for (String imageUrl : dto.getImageUrls()) {
            entity.addImage(imageUrl);
        }

        return entity;
    }

    public List<Slider> toEntities(List<SliderDto> dtos) {
        List<Slider> entities = new ArrayList<>();
        for (SliderDto dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}


