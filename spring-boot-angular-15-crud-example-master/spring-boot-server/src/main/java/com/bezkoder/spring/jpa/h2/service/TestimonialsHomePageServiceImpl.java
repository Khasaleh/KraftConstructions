package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.TestimonialsHomePage;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.repository.TestimonialsHomePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestimonialsHomePageServiceImpl implements TestimonialsHomePageService{
    @Autowired
    private TestimonialsHomePageRepository repository;
    @Override
    public List<TestimonialsHomePage> getAllTestimonials() {
        return repository.findAll();
    }

    @Override
    public TestimonialsHomePage getTestimonialById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NO_CONTENT,"NOT FOUND","Testimonial not found with id: " + id));
    }
    @Override
    public List<TestimonialsHomePage> addTestimonials(List<TestimonialsHomePage> testimonials) {
        return repository.saveAll(testimonials);
    }

    @Override
    public TestimonialsHomePage updateTestimonial(Long id, TestimonialsHomePage testimonial) {
        TestimonialsHomePage existingTestimonial = repository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NO_CONTENT,"NOT FOUND","Testimonial not found with id: " + id));

        existingTestimonial.setHeading(testimonial.getHeading());
        existingTestimonial.setDescription(testimonial.getDescription());
        existingTestimonial.setName(testimonial.getName());

        return repository.save(existingTestimonial);
    }
    @Override
    public void deleteTestimonialById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllTestimonials() {
        repository.deleteAll();
    }
}
