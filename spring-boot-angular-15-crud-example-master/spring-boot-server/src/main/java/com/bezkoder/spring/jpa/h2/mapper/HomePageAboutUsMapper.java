package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class HomePageAboutUsMapper {

    public HomePage fromDto(HomePageAboutUsRequestDTO dto) {
        HomePage entity = new HomePage();
        entity.setAboutusLink(dto.getLink());
        return entity;
    }

    public  HomePageAboutUsResponseDTO toDto(HomePage entity) {
        HomePageAboutUsResponseDTO dto = new HomePageAboutUsResponseDTO();
        dto.setId(entity.getId());
        dto.setLink(entity.getAboutusLink());
        dto.setDescription(entity.getAboutusDescription());
        dto.setVideoUrl(entity.getAboutusVideoUrl());
        return dto;
    }


}

