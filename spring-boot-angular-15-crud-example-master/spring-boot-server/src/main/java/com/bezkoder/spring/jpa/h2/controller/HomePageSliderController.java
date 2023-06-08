package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.HomePageSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.HomePageSliderMapper;
import com.bezkoder.spring.jpa.h2.service.HomePageSliderService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/homepage-banner")
public class HomePageSliderController {
    @Autowired
    private HomePageSliderService homePageSliderService;
    @Autowired
    private HomePageSliderMapper homePageSliderMapper;



    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_USER + "')")
    public ResponseEntity<HomePageSliderResponseDto> uploadSliderImage(@PathVariable("id") Long id, HomePageSliderRequestDto requestDTO) throws IOException {
        if (requestDTO.getId() < 1 || requestDTO.getId() > 5) {
            return ResponseEntity.badRequest().build();
        }

        HomePageSliderResponseDto responseDTO = homePageSliderService.saveOrUpdateSliderImage(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomePageSliderResponseDto> getSliderImage(@PathVariable("id") Long id) {
        if (id < 1 || id > 5) {
            return ResponseEntity.notFound().build();
        }
        HomePageSliderResponseDto responseDTO = homePageSliderService.getSliderImageById(id);
        if (responseDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping
    public ResponseEntity<List<HomePageSliderResponseDto>> getAllSliderImages() {
        List<HomePageSliderResponseDto> sliderImages = homePageSliderService.getAllSliderImages();
        if (sliderImages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sliderImages);
    }
}
