package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.HomePageSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import com.bezkoder.spring.jpa.h2.dto.MessageResponse;
import com.bezkoder.spring.jpa.h2.mapper.HomePageSliderMapper;
import com.bezkoder.spring.jpa.h2.service.HomePageSliderService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/homepage-banner")
public class HomePageSliderController {
    @Autowired
    private HomePageSliderService homePageSliderService;

    @Autowired
    private HomePageSliderMapper homePageSliderMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> uploadSliderImage(HomePageSliderRequestDto homePageSliderRequestDto) throws IOException {
        HomePageSliderResponseDto responseDto = homePageSliderService.uploadMultipleImages(homePageSliderRequestDto);
        return ResponseEntity.ok(new MessageResponse("Images Uploaded Successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomePageSliderResponseDto> getSliderImage(@PathVariable("id") Long id) {
        HomePageSliderResponseDto responseDto = homePageSliderService.getSliderImageById(id);
        if (responseDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<HomePageSliderResponseDto>> getAllSliderImages() {
        List<HomePageSliderResponseDto> sliderImages = homePageSliderService.getAllSliderImages();
        if (sliderImages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sliderImages);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> deleteSliderImage(@PathVariable("id") Long id) {
        homePageSliderService.deleteSliderImageById(id);
        return ResponseEntity.ok(new MessageResponse("Image Deleted Successfully!"));
    }
}
