package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AboutUsService {

    AboutUs getAboutUs(Long id);

    AboutUs updateAboutUs(Long id, AboutUsRequestDTO aboutUsRequestDto);

    void uploadAboutUsImage(Long id, MultipartFile image);
}
