package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.dto.BannerRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.BannerResponseDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceHomePageResponseDto;

import java.util.List;

public interface HomePageAboutUsService {

    HomePage getHomePageAboutUs(Long id);

     HomePage updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO);

    void updateAboutUsVideoUrl(Long id, String videoUrl);


    String getAboutUsVideoUrl(Long id);

    public String addServiceToHomePage(Long id, List<Long> serviceIds);

    public List<ServiceHomePageResponseDto> getServicesByHomePageId(Long id);
    HomePage updateBanner(Long id, BannerRequestDTO bannerRequestDTO);
    BannerResponseDTO getBanner(Long id);
    boolean updateLinkStatus(Long id);
}
