package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsFooterResponseDto;
import com.bezkoder.spring.jpa.h2.dto.AboutUsResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AboutUsMapper {

    public AboutUsResponseDTO toDto(AboutUs entity) {
        AboutUsResponseDTO dto = new AboutUsResponseDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }
    public AboutUsFooterResponseDto footerDto(AboutUs entity) {
        AboutUsFooterResponseDto dto = new AboutUsFooterResponseDto();
        dto.setTitle(entity.getTitle());
        dto.setFooterImageUrl(entity.getFooterImage());
        return dto;
    }


}

