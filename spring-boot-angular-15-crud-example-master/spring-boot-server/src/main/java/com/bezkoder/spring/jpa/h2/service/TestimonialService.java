package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.entity.Testimonial;
import com.bezkoder.spring.jpa.h2.entity.TestimonialPage;
import com.bezkoder.spring.jpa.h2.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    @Autowired
    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    public List<Testimonial> getTestimonialsByPage(TestimonialPage page) {
        return testimonialRepository.findByPage(page);
    }

    public Testimonial addTestimonialImage(MultipartFile image, TestimonialPage page) throws IOException {
        String imageUrl = saveImage(image);
        Testimonial testimonial = new Testimonial();
        //testimonial.setName(name);
        System.out.println(page);
        testimonial.setImageUrl(imageUrl);
        testimonial.setPage(page);
        return testimonialRepository.save(testimonial);

    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path uploadPath = Paths.get("uploads/testimonials");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new IOException("Could not save image: " + fileName, e);
        }
    }

}
