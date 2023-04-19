package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServicesController {


    @Autowired
    public UserServiceImpl userServiceImpl;

    @PostMapping("/addservices")
    public ResponseEntity<ServicesRequestDTO> addService(@RequestBody ServicesRequestDTO servicesRequestDTO) {
        userServiceImpl.addService(servicesRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServicesRequestDTO>> getServices() {
        List<ServicesRequestDTO> services = userServiceImpl.getServices();
        return ResponseEntity.ok(services);
    }

    @PostMapping("/disableAndEnableTheService")
    public ResponseEntity<?> disableAndEnableTheService(@RequestParam String serviceName,
                                                        @RequestParam boolean isActive) {
        userServiceImpl.disableAndEnableTheService(serviceName, isActive);
        return ResponseEntity.ok().build();
    }


}



