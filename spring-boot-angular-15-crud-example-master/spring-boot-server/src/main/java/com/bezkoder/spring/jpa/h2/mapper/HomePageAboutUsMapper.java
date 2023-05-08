package com.bezkoder.spring.jpa.h2.mapper;

<<<<<<< HEAD
import com.bezkoder.spring.jpa.h2.Entity.HomePage;
=======
import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class HomePageAboutUsMapper {

<<<<<<< HEAD
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
=======
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
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
        return dto;
    }


}

