package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.service.SliderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/sliders")
public class SliderController {

    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }


    @PostMapping("/add")
    public ResponseEntity<SliderDto> addSlider(@Valid @RequestParam("images") MultipartFile[] images) {
        try {

            SliderDto sliderDto = sliderService.addSlider(images);


            return new ResponseEntity<>(sliderDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<SliderDto> getAllSliders() {
        return sliderService.getAllSliders();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSlider(@PathVariable("id") Long id) {

        sliderService.deleteSlider(id);

        return ResponseEntity.ok().body("Slider deleted successfully");
    }
}