package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.AboutUsResponseDTO;
import com.bezkoder.spring.jpa.h2.entity.AboutUs;
import org.springframework.stereotype.Component;

@Component
public class AboutUsMapper {

    public AboutUs fromDto(AboutUsRequestDTO dto) {
        AboutUs entity = new AboutUs();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public AboutUsResponseDTO toDto(AboutUs entity) {
        AboutUsResponseDTO dto = new AboutUsResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}

