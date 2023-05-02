package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.DetailsDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;
import com.bezkoder.spring.jpa.h2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServicesController {


    @Autowired
    public UserServiceImpl userServiceImpl;

    @PostMapping("/addservices")
    public ResponseEntity<ServicesResponseDTO> addService(@Valid @RequestBody ServicesRequestDTO servicesRequestDTO) {
        ServicesResponseDTO addservice = userServiceImpl.addService(servicesRequestDTO);
        return ResponseEntity.ok(addservice);
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServicesRequestDTO>> getServices() {
        List<ServicesRequestDTO> services = userServiceImpl.getServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping
    public List<DetailsDTO> getAllServicesDetailsWithName() {
        return userServiceImpl.getAllServicesDetailsWithName();
    }

    @PatchMapping("/{id}/enable")
    public boolean enableService(@PathVariable Long id) {
        return userServiceImpl.disableAndEnableTheService(id, true);
    }

    @PatchMapping("/{id}/disable")
    public boolean disableService(@PathVariable Long id) {
        return userServiceImpl.disableAndEnableTheService(id, false);
    }

    @PutMapping("/update-service/{id}")
    public ResponseEntity<ServicesResponseDTO> updateService(@PathVariable Long id, @RequestBody ServicesRequestDTO dto) {
        ServicesResponseDTO result = userServiceImpl.updateService(id, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete-service/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        userServiceImpl.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}



