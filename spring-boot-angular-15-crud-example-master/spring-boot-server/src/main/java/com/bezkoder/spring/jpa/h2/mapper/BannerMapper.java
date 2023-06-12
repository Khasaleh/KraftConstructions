package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.dto.BannerRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.BannerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {
    
    public static HomePage mapToEntity(BannerRequestDTO requestDTO, HomePage homePage) {
        homePage.setBannerLink(requestDTO.getBannerLink());
        homePage.setBannerDescription(requestDTO.getBannerDescription());
        homePage.setLinkStatus(requestDTO.isLinkStatus());
        return homePage;
    }
    
    public static BannerResponseDTO mapToResponseDTO(HomePage homePage) {
        BannerResponseDTO responseDTO = new BannerResponseDTO();
        responseDTO.setBannerLink(homePage.getBannerLink());
        responseDTO.setBannerDescription(homePage.getBannerDescription());
        responseDTO.setLinkStatus(homePage.isLinkStatus());
        return responseDTO;
    }
}