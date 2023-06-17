package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.*;
import com.bezkoder.spring.jpa.h2.mapper.AboutUsMapper;
import com.bezkoder.spring.jpa.h2.service.AboutUsService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/about-us")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private AboutUsMapper aboutUsMapper;


    private static final Long ABOUT_US_ID = 1L;

    @PostMapping("/update-description")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> updateAboutUs(@RequestBody AboutUsRequestDTO aboutUsRequestDto) {
        AboutUs aboutUs = aboutUsService.updateAboutUs(ABOUT_US_ID, aboutUsRequestDto);
        AboutUsResponseDTO aboutUsResponseDto = aboutUsMapper.toDto(aboutUs);
        return ResponseEntity.ok(new MessageResponse("AboutUs Updated Successfully"));
    }

    @GetMapping
    public ResponseEntity<AboutUsResponseDTO> getAboutUs() {
        AboutUs aboutUs = aboutUsService.getAboutUs(ABOUT_US_ID);
        AboutUsResponseDTO aboutUsResponseDto = aboutUsMapper.toDto(aboutUs);
        return ResponseEntity.ok(aboutUsResponseDto);
    }
    @PostMapping("/upload-image")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> uploadImage(@Valid MultipartFile image){
        aboutUsService.uploadAboutUsImage(ABOUT_US_ID,image);
        return ResponseEntity.ok(new MessageResponse("Image Updated Successfully"));
    }
    @PostMapping("/update-footer")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> createOrUpdateFooterImageAndTitle(AboutUsFooterRequestDto requestDto) throws IOException {
        aboutUsService.createOrUpdateFooterImageAndTitle(ABOUT_US_ID,requestDto);
        return ResponseEntity.ok(new MessageResponse("Footer Updated Successfully"));
    }
    @GetMapping("/footer")
    public ResponseEntity<AboutUsFooterResponseDto> getAboutUsFooter() {
        AboutUs aboutUs = aboutUsService.getAboutUs(ABOUT_US_ID);
        AboutUsFooterResponseDto aboutUsResponseDto = aboutUsMapper.footerDto(aboutUs);
        return ResponseEntity.ok(aboutUsResponseDto);
    }
}


