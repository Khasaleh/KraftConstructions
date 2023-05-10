package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;

public interface HomePageAboutUsService {

    HomePageAboutUs getHomePageAboutUs(Long id);

     HomePageAboutUs updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO);

    void updateAboutUsVideoUrl(Long id, String videoUrl);


    String getAboutUsVideoUrl(Long id);

}
