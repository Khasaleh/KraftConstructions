package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsDTO;
import com.bezkoder.spring.jpa.h2.mapper.ServiceDetailsMapper;
import com.bezkoder.spring.jpa.h2.service.ServicesDetailsServiceImpl;
import com.bezkoder.spring.jpa.h2.service.UserDetailsImpl;
import com.bezkoder.spring.jpa.h2.service.UserServiceImpl;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class ServicesDetailsController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ServicesDetailsServiceImpl servicesDetailsService;

    @Autowired
    private ServiceDetailsMapper serviceDetailsMapper;


    @PostMapping("/{serviceId}/details")
    @PreAuthorize("hasRole('" + Roles.ROLE_AUTHOR + "')")
    public ResponseEntity<ServiceDetailsDTO> createServiceDetails(@PathVariable Long serviceId, @Valid @RequestBody ServiceDetailsDTO serviceDetailsDTO) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        serviceDetailsDTO.setServices_id(serviceId);
        ServiceDetailsDTO createdServiceDetailsDTO = servicesDetailsService.createServiceDetails1(serviceDetailsDTO, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceDetailsDTO);
    }


    @GetMapping("/{detailId}")
    public ResponseEntity<ServiceDetailsDTO> getServiceDetail(@PathVariable Long detailId) {
        ServiceDetailsDTO serviceDetailsDTO = servicesDetailsService.getServiceDetailsById(detailId);
        return ResponseEntity.ok(serviceDetailsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDetailsDTO> updateServicesDetails(@PathVariable Long id, @RequestBody ServiceDetailsDTO servicesDetailsDTO) {
        ServiceDetailsDTO updatedServicesDetailsDTO = servicesDetailsService.updateServicesDetails(id, servicesDetailsDTO);
        if (updatedServicesDetailsDTO != null) {
            return ResponseEntity.ok(updatedServicesDetailsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{detailId}")
    public ResponseEntity<Void> deleteServiceDetail(@PathVariable Long detailId) {
        servicesDetailsService.deleteServiceDetails(detailId);
        return ResponseEntity.ok().build();
    }
}
