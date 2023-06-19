package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.MessageResponse;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.TestimonialSliderMapper;
import com.bezkoder.spring.jpa.h2.service.TestimonialSliderService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/testimonial-slider")
public class TestimonialSliderController {
    @Autowired
    private TestimonialSliderService testimonialSliderService;

    @Autowired
    private TestimonialSliderMapper testimonialSliderMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> uploadSliderImage(TestimonialSliderRequestDto requestDto) throws IOException {
        TestimonialSliderResponseDto responseDto = testimonialSliderService.uploadMultipleImages(requestDto);
        return ResponseEntity.ok(new MessageResponse("Images Uploaded Successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestimonialSliderResponseDto> getSliderImage(@PathVariable("id") Long id) {
        TestimonialSliderResponseDto responseDto = testimonialSliderService.getSliderImageById(id);
        if (responseDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<TestimonialSliderResponseDto>> getAllSliderImages() {
        List<TestimonialSliderResponseDto> sliderImages = testimonialSliderService.getAllSliderImages();
        if (sliderImages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sliderImages);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> deleteSliderImage(@PathVariable("id") Long id) {
        testimonialSliderService.deleteSliderImageById(id);
        return ResponseEntity.ok(new MessageResponse("Image Deleted Successfully"));
    }
}
