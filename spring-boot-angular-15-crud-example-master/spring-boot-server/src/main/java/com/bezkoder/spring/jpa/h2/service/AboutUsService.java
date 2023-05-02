package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;

public interface AboutUsService {

    AboutUs getAboutUs(Long id);

    AboutUs updateAboutUs(Long id, AboutUsRequestDTO aboutUsRequestDto);

   void updateAboutUsVideoUrl(Long id, String videoUrl);


    String getAboutUsVideoUrl(Long id);

}
