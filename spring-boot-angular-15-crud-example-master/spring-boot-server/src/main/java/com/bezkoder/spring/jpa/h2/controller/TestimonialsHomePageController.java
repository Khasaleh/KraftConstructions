package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.TestimonialsHomePage;
import com.bezkoder.spring.jpa.h2.dto.TestimonialHomePageRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.TestimonialHomePageResponseDTO;
import com.bezkoder.spring.jpa.h2.mapper.TestimonialsHomePageMapper;
import com.bezkoder.spring.jpa.h2.service.TestimonialsHomePageService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/testimonial-homepage")
public class TestimonialsHomePageController {
    @Autowired
    private TestimonialsHomePageService testimonialService;
    @Autowired
    private TestimonialsHomePageMapper testimonialMapper;

    @GetMapping
    public ResponseEntity<List<TestimonialHomePageResponseDTO>> getAllTestimonials() {
        List<TestimonialsHomePage> testimonials = testimonialService.getAllTestimonials();
        List<TestimonialHomePageResponseDTO> responseDTOs = testimonialMapper.toResponseDTOs(testimonials);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestimonialHomePageResponseDTO> getTestimonialById(@PathVariable Long id) {
        TestimonialsHomePage testimonial = testimonialService.getTestimonialById(id);
        TestimonialHomePageResponseDTO responseDTO = testimonialMapper.toResponseDTO(testimonial);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<List<TestimonialHomePageResponseDTO>> addTestimonials(@RequestBody List<TestimonialHomePageRequestDTO> testimonials) {
        List<TestimonialsHomePage> testimonialEntities = testimonialMapper.toEntities(testimonials);
        List<TestimonialsHomePage> savedTestimonials = testimonialService.addTestimonials(testimonialEntities);
        List<TestimonialHomePageResponseDTO> responseDTOs = testimonialMapper.toResponseDTOs(savedTestimonials);
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<TestimonialHomePageResponseDTO> updateTestimonial(
            @PathVariable Long id, @RequestBody TestimonialHomePageRequestDTO testimonial) {
        TestimonialsHomePage testimonialEntity = testimonialMapper.toEntity(testimonial);
        TestimonialsHomePage updatedTestimonial = testimonialService.updateTestimonial(id, testimonialEntity);
        TestimonialHomePageResponseDTO responseDTO = testimonialMapper.toResponseDTO(updatedTestimonial);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<Void> deleteTestimonialById(@PathVariable Long id) {
        testimonialService.deleteTestimonialById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<Void> deleteAllTestimonials() {
        testimonialService.deleteAllTestimonials();
        return ResponseEntity.ok().build();
    }

}
