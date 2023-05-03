package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.AboutUsResponseDTO;
import com.bezkoder.spring.jpa.h2.mapper.AboutUsMapper;
import com.bezkoder.spring.jpa.h2.service.AboutUsService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/about-us")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private AboutUsMapper aboutUsMapper;


    private static final Long ABOUT_US_ID = 1L;

    @PostMapping("/update-description")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_USER + "')")
    public ResponseEntity<AboutUsResponseDTO> updateAboutUs(@RequestBody AboutUsRequestDTO aboutUsRequestDto) {
        AboutUs aboutUs = aboutUsService.updateAboutUs(ABOUT_US_ID, aboutUsRequestDto);
        AboutUsResponseDTO aboutUsResponseDto = aboutUsMapper.toDto(aboutUs);
        return ResponseEntity.ok(aboutUsResponseDto);
    }

    @GetMapping
    public ResponseEntity<AboutUsResponseDTO> getAboutUs() {
        AboutUs aboutUs = aboutUsService.getAboutUs(ABOUT_US_ID);
        AboutUsResponseDTO aboutUsResponseDto = aboutUsMapper.toDto(aboutUs);
        return ResponseEntity.ok(aboutUsResponseDto);
    }

}


