package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.ContactUs;
import com.bezkoder.spring.jpa.h2.service.ContactUsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactUsController {

    private final ContactUsService contactUsService;


    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping("/contact-us")
    public ResponseEntity<ContactUs> submitContactUsForm(@RequestBody ContactUs contactUs) {
        ContactUs submittedContactUs = contactUsService.submitContactUsForm(contactUs);
        return ResponseEntity.ok(submittedContactUs);
    }

    @GetMapping("/contact-us")
    public ResponseEntity<List<ContactUs>> getAllContactUsForms() {
        List<ContactUs> contactUsForms = contactUsService.getAllContactUsForms();
        return ResponseEntity.ok(contactUsForms);
    }



}
