package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceWithDetailDTO;
import com.bezkoder.spring.jpa.h2.mapper.ServiceDetailsMapper;
import com.bezkoder.spring.jpa.h2.service.ServicesDetailsServiceImpl;
import com.bezkoder.spring.jpa.h2.service.UserDetailsImpl;
import com.bezkoder.spring.jpa.h2.service.ServicesServiceImpl;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/services")
public class ServicesDetailsController {

    @Autowired
    private ServicesServiceImpl servicesServiceImpl;
    @Autowired
    private ServicesDetailsServiceImpl servicesDetailsService;

    @Autowired
    private ServiceDetailsMapper serviceDetailsMapper;


    @PostMapping("/addDetails")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<ServiceDetailsDTO> addServiceDetails(@Valid @RequestBody ServiceDetailsDTO serviceDetailsDTO) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ServiceDetailsDTO createdServiceDetailsDTO = servicesDetailsService.addServiceDetails(serviceDetailsDTO, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceDetailsDTO);
    }


    @GetMapping("/{serviceId}/details")
    public ResponseEntity<ServiceDetailsDTO> getServiceDetailsByServiceId(@PathVariable Long serviceId) {
        ServiceDetailsDTO serviceDetails = servicesDetailsService.getServiceDetailsByServiceId(serviceId);
        if (serviceDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(serviceDetails);
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<ServiceDetailsDTO> updateServicesDetails(@PathVariable Long id, @RequestBody ServiceDetailsDTO servicesDetailsDTO) {
        ServiceDetailsDTO updatedServicesDetailsDTO = servicesDetailsService.updateServicesDetails(id, servicesDetailsDTO);
        if (updatedServicesDetailsDTO != null) {
            return ResponseEntity.ok(updatedServicesDetailsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<ServiceWithDetailDTO>> getAllServicesDetailsWithName() {
        List<ServiceWithDetailDTO> list = servicesDetailsService.getAllServicesDetailsWithName();
        return ResponseEntity.ok(list);
    }


}
