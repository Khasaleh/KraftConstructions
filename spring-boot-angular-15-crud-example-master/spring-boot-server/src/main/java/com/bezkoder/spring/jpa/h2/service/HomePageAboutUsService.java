package com.bezkoder.spring.jpa.h2.service;

<<<<<<< HEAD
import com.bezkoder.spring.jpa.h2.Entity.HomePage;
=======
import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;

public interface HomePageAboutUsService {

<<<<<<< HEAD
    HomePage getHomePageAboutUs(Long id);

     HomePage updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO);
=======
    HomePageAboutUs getHomePageAboutUs(Long id);

     HomePageAboutUs updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO);
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d

    void updateAboutUsVideoUrl(Long id, String videoUrl);


    String getAboutUsVideoUrl(Long id);

}
