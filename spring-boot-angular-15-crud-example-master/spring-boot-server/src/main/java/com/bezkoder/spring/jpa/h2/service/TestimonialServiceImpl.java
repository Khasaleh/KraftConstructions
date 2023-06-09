package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.Testimonial;
import com.bezkoder.spring.jpa.h2.Entity.TestimonialPage;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.repository.TestimonialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }


    @Override
    public List<Testimonial> getTestimonialsByPage(TestimonialPage page) {
        return testimonialRepository.findByPage(page);
    }

    @Override
    public Testimonial addTestimonialImage(MultipartFile image, TestimonialPage page) {
        Testimonial testimonial = new Testimonial();
        testimonial.setPage(page);

        String imageUrl = null;
        try {
            imageUrl = saveImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        testimonial.setImageUrl(imageUrl);

        return testimonialRepository.save(testimonial);
    }

@Override
public String saveImage(MultipartFile image) throws IOException {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
    Path uploadPath = Paths.get("uploads/testimonials");
    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    }
    InputStream inputStream = image.getInputStream();
    Path filePath = uploadPath.resolve(fileName);
    String relativePath = "/testimonials/" + uploadPath.relativize(filePath).toString();
    return relativePath;
}
    @Override
    public void deleteTestimonialImage(Long id) {

        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Entity not found for id: " +id,"Testimonial not found"));
        try {
            Files.deleteIfExists(Paths.get(testimonial.getImageUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        testimonialRepository.deleteById(id);
    }



}

