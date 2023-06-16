package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsFooterRequestDto;
import com.bezkoder.spring.jpa.h2.dto.AboutUsFooterResponseDto;
import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AboutUsService {

    AboutUs getAboutUs(Long id);

    AboutUs updateAboutUs(Long id, AboutUsRequestDTO aboutUsRequestDto);

    void uploadAboutUsImage(Long id, MultipartFile image);
    AboutUsFooterResponseDto createOrUpdateFooterImageAndTitle(Long id, AboutUsFooterRequestDto requestDto) throws IOException;
}
