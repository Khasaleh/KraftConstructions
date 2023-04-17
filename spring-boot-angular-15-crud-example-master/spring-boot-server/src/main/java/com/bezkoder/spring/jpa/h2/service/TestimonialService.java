package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.Testimonial;
import com.bezkoder.spring.jpa.h2.Entity.TestimonialPage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TestimonialService {
    List<Testimonial> getTestimonialsByPage(TestimonialPage page);
    Testimonial addTestimonialImage(MultipartFile image, TestimonialPage page);
    String saveImage(MultipartFile image) throws IOException;

    void deleteTestimonialImage(Long id);


}
