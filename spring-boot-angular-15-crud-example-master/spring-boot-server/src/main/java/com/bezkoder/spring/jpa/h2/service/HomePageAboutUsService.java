package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;

public interface HomePageAboutUsService {

    HomePage getHomePageAboutUs(Long id);

     HomePage updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO);

    void updateAboutUsVideoUrl(Long id, String videoUrl);


    String getAboutUsVideoUrl(Long id);


}
