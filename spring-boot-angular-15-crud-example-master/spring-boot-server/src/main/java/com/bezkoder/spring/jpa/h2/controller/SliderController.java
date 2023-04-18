package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.Entity.Slider;
import com.bezkoder.spring.jpa.h2.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sliders")
public class SliderController {

    private final SliderService sliderService;

    @Autowired
    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @PostMapping
    public ResponseEntity<SliderDto> addSlider(@Valid @RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {

        // create Slider entity
        Slider slider = new Slider();

        // save image urls in entity
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            String imageUrl = saveImage(file);
            imageUrls.add(imageUrl);
        }
        slider.setImageUrls(imageUrls);

        // save entity using service
        SliderDto savedSlider = sliderService.addSlider(slider);

        return ResponseEntity.ok(savedSlider);
    }
    private String getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElse("");
    }

    private String saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String extension = getFileExtension(fileName);
        String newFileName = UUID.randomUUID().toString() + "." + extension;
        String imageUrl = "http://example.com/images/" + newFileName; // replace with actual image url


        return imageUrl;
    }

}