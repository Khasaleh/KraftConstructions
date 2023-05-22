package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class HomePageAboutUsMapper {

    public HomePage fromDto(HomePageAboutUsRequestDTO dto) {
        HomePage entity = new HomePage();
        entity.setAboutusLink(dto.getAboutusLink());
        return entity;
    }

    public  HomePageAboutUsResponseDTO toDto(HomePage entity) {
        HomePageAboutUsResponseDTO dto = new HomePageAboutUsResponseDTO();
        dto.setId(entity.getId());
        dto.setAboutusLink(entity.getAboutusLink());
        dto.setAboutusDescription(entity.getAboutusDescription());
        dto.setAboutusVideoUrl(entity.getAboutusVideoUrl());
        return dto;
    }


}

