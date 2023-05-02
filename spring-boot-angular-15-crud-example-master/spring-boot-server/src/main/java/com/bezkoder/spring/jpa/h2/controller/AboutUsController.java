package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.AboutUsResponseDTO;
import com.bezkoder.spring.jpa.h2.mapper.AboutUsMapper;
import com.bezkoder.spring.jpa.h2.service.AboutUsService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/about-us")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private AboutUsMapper aboutUsMapper;


    private static final Long ABOUT_US_ID = 1L;

    @PostMapping("/update-description")
    //@PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_USER + "')")
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
        @PostMapping("/upload-video")
   // @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<Void> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        String videoUrl = saveVideoToFileSystem(file);
       aboutUsService.updateAboutUsVideoUrl(ABOUT_US_ID, videoUrl);
        return ResponseEntity.ok().build();
    }

    private String saveVideoToFileSystem(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(fileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        Path path = Paths.get("uploads/videos/" + storedFileName);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return path.toAbsolutePath().toString();
    }
    @GetMapping("/video-url")
    public ResponseEntity<String> getVideoUrl() {
        AboutUs aboutUs = aboutUsService.getAboutUs(ABOUT_US_ID);
        String videoUrl = aboutUs.getVideoUrl();
        return ResponseEntity.ok(videoUrl);
    }


}


