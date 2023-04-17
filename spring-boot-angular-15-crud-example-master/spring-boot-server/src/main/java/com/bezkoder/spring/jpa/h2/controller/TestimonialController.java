package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.Testimonial;
import com.bezkoder.spring.jpa.h2.Entity.TestimonialPage;
import com.bezkoder.spring.jpa.h2.service.TestimonialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    private final TestimonialServiceImpl testimonialService;

    @Autowired
    public TestimonialController(TestimonialServiceImpl testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping("/{page}")
    public List<Testimonial> getTestimonialsByPage(@PathVariable TestimonialPage page) {
        return testimonialService.getTestimonialsByPage(page);
    }

    @PostMapping("/images")
    public Testimonial addTestimonialImage(@Valid @RequestParam("image") MultipartFile image,
                                           @RequestParam("page") TestimonialPage page) throws IOException {
        return testimonialService.addTestimonialImage(image, page);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestimonialImage(@PathVariable Long id) {
        testimonialService.deleteTestimonialImage(id);
        return ResponseEntity.ok("Testimonial image deleted successfully");
    }

}
