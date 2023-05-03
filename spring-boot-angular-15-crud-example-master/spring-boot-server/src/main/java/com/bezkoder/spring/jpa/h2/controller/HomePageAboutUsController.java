package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsResponseDTO;
import com.bezkoder.spring.jpa.h2.mapper.HomePageAboutUsMapper;
import com.bezkoder.spring.jpa.h2.service.HomePageAboutUsService;
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
@RequestMapping("/api/homepageabout-us")
public class HomePageAboutUsController {

    @Autowired
    private HomePageAboutUsService homePageAboutUsService;

    @Autowired
    private HomePageAboutUsMapper homePageAboutUsMapper;


    private static final Long ABOUT_US_ID = 1L;

    @PostMapping("/homepageupdate-description")
    //@PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_USER + "')")
    public ResponseEntity<HomePageAboutUsResponseDTO> updateHomePageAboutUs(@RequestBody HomePageAboutUsRequestDTO homePageAboutUsRequestDTO) {
        HomePageAboutUs homePageAboutUs = homePageAboutUsService.updateHomePageAboutUs(ABOUT_US_ID, homePageAboutUsRequestDTO);
        HomePageAboutUsResponseDTO homePageAboutUsResponseDTO = homePageAboutUsMapper.toDto(homePageAboutUs);
        return ResponseEntity.ok(homePageAboutUsResponseDTO);
    }

    @GetMapping
    public ResponseEntity<HomePageAboutUsResponseDTO> getAboutUs() {
        HomePageAboutUs homePageAboutUs = homePageAboutUsService.getHomePageAboutUs(ABOUT_US_ID);
        HomePageAboutUsResponseDTO homePageAboutUsResponseDTO = homePageAboutUsMapper.toDto(homePageAboutUs);
        return ResponseEntity.ok(homePageAboutUsResponseDTO);
    }
    @PostMapping("/upload-video")
    // @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<Void> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        String videoUrl = saveVideoToFileSystem(file);
        homePageAboutUsService.updateAboutUsVideoUrl(ABOUT_US_ID, videoUrl);
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
        String videoUrl = homePageAboutUsService.getAboutUsVideoUrl(ABOUT_US_ID);
        return ResponseEntity.ok(videoUrl);
    }
}


