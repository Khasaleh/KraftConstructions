package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.dto.*;
import com.bezkoder.spring.jpa.h2.mapper.HomePageAboutUsMapper;
import com.bezkoder.spring.jpa.h2.service.HomePageAboutUsService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/homepageabout-us")
public class HomePageAboutUsController {

    @Autowired
    private HomePageAboutUsService homePageAboutUsService;

    @Autowired
    private HomePageAboutUsMapper homePageAboutUsMapper;


    private static final Long ABOUT_US_ID = 1L;

    @PostMapping("/homepageupdate-description")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<HomePageAboutUsResponseDTO> updateHomePageAboutUs(@RequestBody HomePageAboutUsRequestDTO homePageAboutUsRequestDTO) {
        HomePage homePage = homePageAboutUsService.updateHomePageAboutUs(ABOUT_US_ID, homePageAboutUsRequestDTO);
        HomePageAboutUsResponseDTO homePageAboutUsResponseDTO = homePageAboutUsMapper.toDto(homePage);
        return ResponseEntity.ok(homePageAboutUsResponseDTO);
    }

    @GetMapping
    public ResponseEntity<HomePageAboutUsResponseDTO> getAboutUs() {
        HomePage homePage = homePageAboutUsService.getHomePageAboutUs(ABOUT_US_ID);
        HomePageAboutUsResponseDTO homePageAboutUsResponseDTO = homePageAboutUsMapper.toDto(homePage);
        return ResponseEntity.ok(homePageAboutUsResponseDTO);
    }
    @PostMapping("/upload-video")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<String> uploadVideo(@Valid @RequestParam("file") MultipartFile file) throws IOException {
        String videoUrl = saveVideoToFileSystem(file);
        homePageAboutUsService.updateAboutUsVideoUrl(ABOUT_US_ID, videoUrl);
        return ResponseEntity.ok("Video uploaded successfully");
    }

    private String saveVideoToFileSystem(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path uploadPath = Paths.get("uploads/videos");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = file.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        String relativePath = "/videos/" + uploadPath.relativize(filePath).toString();
        return relativePath;
    }

    @GetMapping("/video-url")
    public ResponseEntity<String> getVideoUrl() {
        String videoUrl = homePageAboutUsService.getAboutUsVideoUrl(ABOUT_US_ID);
        return ResponseEntity.ok(videoUrl);
    }

    @PostMapping("/{id}/addservices")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<String> addServiceToHomePage(@PathVariable Long id, @RequestBody ServiceHomePageRequestDto serviceHomeRequestDto) {
        String message = homePageAboutUsService.addServiceToHomePage(id, serviceHomeRequestDto.getServiceIds());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceHomePageResponseDto>> getServicesByHomePageId(@PathVariable Long id) {
        return ResponseEntity.ok(homePageAboutUsService.getServicesByHomePageId(id));
    }
    @PostMapping("/update-banner")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<?> updateBanner(@RequestBody BannerRequestDTO bannerRequestDTO) {
        HomePage homePage = homePageAboutUsService.updateBanner(ABOUT_US_ID, bannerRequestDTO);
        return ResponseEntity.ok(new MessageResponse("Banner Updated Successfully"));
    }
    @GetMapping("/banner")
    public ResponseEntity<BannerResponseDTO> getBanner() {
        BannerResponseDTO bannerResponseDTO = homePageAboutUsService.getBanner(ABOUT_US_ID);
        return ResponseEntity.ok(bannerResponseDTO);
    }
    @PutMapping("/banner-link-status")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<String> updateLinkStatus() {
        boolean updatedLinkStatus = homePageAboutUsService.updateLinkStatus(ABOUT_US_ID);
        if (updatedLinkStatus) {
            return ResponseEntity.ok("Link status updated to true");
        } else {
            return ResponseEntity.ok("Link status updated to false");
        }
    }
}


