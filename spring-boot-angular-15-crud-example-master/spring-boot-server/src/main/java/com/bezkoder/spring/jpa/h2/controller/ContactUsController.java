package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.ContactUs;
import com.bezkoder.spring.jpa.h2.dto.ContactUsDto;
import com.bezkoder.spring.jpa.h2.mapper.ContactUsMapper;
import com.bezkoder.spring.jpa.h2.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact-us")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping
    public ResponseEntity<ContactUsDto> createContactUs(@Valid @RequestBody ContactUsDto contactUsDTO) {
        ContactUsDto createdContactUs = contactUsService.createContactUs(contactUsDTO);
        return new ResponseEntity<>(createdContactUs, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<ContactUsDto>> getAllContactUs() {
        List<ContactUsDto> contactUsDTOList = contactUsService.getAllContactUs();
        return ResponseEntity.ok(contactUsDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactUs(@PathVariable Long id) {
        contactUsService.deleteContactUs(id);
        return ResponseEntity.noContent().build();
    }
}