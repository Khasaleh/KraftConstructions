package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.ContactUsAddress;
import com.bezkoder.spring.jpa.h2.dto.ContactUsAddressRequest;
import com.bezkoder.spring.jpa.h2.dto.ContactUsAddressResponse;
import com.bezkoder.spring.jpa.h2.dto.MessageResponse;
import com.bezkoder.spring.jpa.h2.mapper.ContactUsAddressMapper;
import com.bezkoder.spring.jpa.h2.service.ContactUsAddressService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/contact-address")
public class ContactUsAddressController {
    @Autowired
    private ContactUsAddressService contactUsAddressService;

    @Autowired
    private ContactUsAddressMapper contactUsAddressMapper;
    private static final Long CONTACT_ADDRESS_ID = 1L;
    @PostMapping("/update-address")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<MessageResponse> updateAboutUs(@RequestBody ContactUsAddressRequest contactUsAddressRequest) {
        ContactUsAddress contactUsAddress = contactUsAddressService.updateAddress(CONTACT_ADDRESS_ID, contactUsAddressRequest);
        ContactUsAddressResponse contactUsAddressResponse = contactUsAddressMapper.toDto(contactUsAddress);
        return ResponseEntity.ok(new MessageResponse("Address updated Sucessfully"));
    }

    @GetMapping
    public ResponseEntity<ContactUsAddressResponse> getAboutUs() {
        ContactUsAddress contactUsAddress = contactUsAddressService.getAddress(CONTACT_ADDRESS_ID);
        ContactUsAddressResponse contactUsAddressResponse = contactUsAddressMapper.toDto(contactUsAddress);
        return ResponseEntity.ok(contactUsAddressResponse);
    }
}
