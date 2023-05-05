package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class HomePageAboutUsMapper {

    public HomePageAboutUs fromDto(HomePageAboutUsRequestDTO dto) {
        HomePageAboutUs entity = new HomePageAboutUs();
        entity.setLink(dto.getLink());
        return entity;
    }

    public  HomePageAboutUsResponseDTO toDto(HomePageAboutUs entity) {
        HomePageAboutUsResponseDTO dto = new HomePageAboutUsResponseDTO();
        dto.setId(entity.getId());
        dto.setLink(entity.getLink());
        dto.setDescription(entity.getDescription());
        dto.setVideoUrl(entity.getVideoUrl());
        return dto;
    }


}

