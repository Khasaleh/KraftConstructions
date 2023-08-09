package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsResponseDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceWithDetailDTO;
import com.bezkoder.spring.jpa.h2.mapper.ServiceDetailsMapper;
import com.bezkoder.spring.jpa.h2.service.ServicesDetailsServiceImpl;
import com.bezkoder.spring.jpa.h2.service.ServicesServiceImpl;
import com.bezkoder.spring.jpa.h2.service.UserDetailsImpl;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4300)
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
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<ServiceDetailsResponseDTO> addServiceDetails(ServiceDetailsRequestDTO serviceDetailsRequestDTO) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ServiceDetailsResponseDTO createdServiceDetailsResponseDTO = servicesDetailsService.addServiceDetails(serviceDetailsRequestDTO, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceDetailsResponseDTO);
    }


    @GetMapping("/{serviceId}/details")
    public ResponseEntity<ServiceDetailsResponseDTO> getServiceDetailsByServiceId(@PathVariable Long serviceId) {
        ServiceDetailsResponseDTO serviceDetails = servicesDetailsService.getServiceDetailsByServiceId(serviceId);
        if (serviceDetails == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(serviceDetails);
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public ResponseEntity<ServiceDetailsResponseDTO> updateServicesDetails(@PathVariable Long id, ServiceDetailsRequestDTO servicesDetailsRequestDTO) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ServiceDetailsResponseDTO updatedServicesDetailsResponseDTO = servicesDetailsService.updateServicesDetails(id, servicesDetailsRequestDTO, userDetails);
        if (updatedServicesDetailsResponseDTO != null) {
            return ResponseEntity.ok(updatedServicesDetailsResponseDTO);
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
