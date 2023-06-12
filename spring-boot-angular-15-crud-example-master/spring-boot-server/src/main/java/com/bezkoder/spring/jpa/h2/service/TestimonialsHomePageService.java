package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.TestimonialsHomePage;

import java.util.List;

public interface TestimonialsHomePageService {
    List<TestimonialsHomePage> getAllTestimonials();

    TestimonialsHomePage getTestimonialById(Long id);

    List<TestimonialsHomePage> addTestimonials(List<TestimonialsHomePage> testimonials);

    TestimonialsHomePage updateTestimonial(Long id, TestimonialsHomePage testimonial);

    void deleteTestimonialById(Long id);

    void deleteAllTestimonials();
}
