package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.PortfolioServiceRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;
import com.bezkoder.spring.jpa.h2.service.ServicesServiceImpl;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServicesController {


    @Autowired
    public ServicesServiceImpl servicesServiceImpl;

    @PostMapping("/addservices")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<ServicesResponseDTO> addService(@Valid @RequestBody ServicesRequestDTO servicesRequestDTO) {
        ServicesResponseDTO addservice = servicesServiceImpl.addService(servicesRequestDTO);
        return ResponseEntity.ok(addservice);
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServicesRequestDTO>> getServices() {
        List<ServicesRequestDTO> services = servicesServiceImpl.getServices();
        return ResponseEntity.ok(services);
    }


    @PatchMapping("/{id}/enable")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public boolean enableService(@PathVariable Long id) {
        return servicesServiceImpl.disableAndEnableTheService(id, true);
    }

    @PatchMapping("/{id}/disable")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public boolean disableService(@PathVariable Long id) {
        return servicesServiceImpl.disableAndEnableTheService(id, false);
    }

    @PutMapping("/update-service/{id}")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<ServicesResponseDTO> updateService(@PathVariable Long id, @RequestBody ServicesRequestDTO dto) {
        ServicesResponseDTO result = servicesServiceImpl.updateService(id, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete-service/{id}")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        servicesServiceImpl.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/service/{id}/portfolios")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<String> addPortfoliosToService(@PathVariable Long id, @RequestBody PortfolioServiceRequestDTO portfolioServiceRequestDTO){
        return ResponseEntity.ok(servicesServiceImpl.addPortfolioToService(id, portfolioServiceRequestDTO));
    }

}



