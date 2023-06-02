package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.CareersApplicationRequestDto;
import com.bezkoder.spring.jpa.h2.dto.CareersApplicationResponseDto;
import com.bezkoder.spring.jpa.h2.service.CareersApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/careers-applications")
public class CareersApplicationController {
    @Autowired
    private CareersApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<CareersApplicationResponseDto>> getAllApplications() {
        List<CareersApplicationResponseDto> responseDtoList = applicationService.getAllApplications();
        return ResponseEntity.ok(responseDtoList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CareersApplicationResponseDto> getApplicationById(@PathVariable Long id) {
        CareersApplicationResponseDto responseDto = applicationService.getApplicationById(id);
        return ResponseEntity.ok(responseDto);
    }
    @PostMapping
    public ResponseEntity<CareersApplicationResponseDto> saveApplication(
            @RequestParam("resume") MultipartFile resume,
            @ModelAttribute CareersApplicationRequestDto requestDto) {

        requestDto.setResume(resume);
        CareersApplicationResponseDto responseDto = applicationService.saveApplication(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.ok("Application with ID " + id + " has been deleted successfully.");
    }
}