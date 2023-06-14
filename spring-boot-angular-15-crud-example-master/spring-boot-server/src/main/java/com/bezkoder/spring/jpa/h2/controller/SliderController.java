package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.service.SliderService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sliders")
public class SliderController {

    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
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
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<String> deleteSlider(@PathVariable("id") Long id) {

        sliderService.deleteSlider(id);

        return ResponseEntity.ok().body("Slider deleted successfully");
    }
    @PutMapping("/{id}/images")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<SliderDto> updateSliderImages(@PathVariable("id") Long id,
                                                        @RequestParam("images") MultipartFile[] images) {
        SliderDto updatedSlider;
        try {
            updatedSlider = sliderService.updateSliderImages(id, images);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (updatedSlider == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(updatedSlider);
    }

}

