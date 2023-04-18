package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    @GetMapping("/servicesList")
//    public ResponseEntity<List<ServicesResponseDTO>> getServices() {
//        List<Services> services = userServiceImpl.getServices();
//        List<ServicesResponseDTO> responseDTOs = new ArrayList<>();
//        for (Services service : services) {
//            ServicesResponseDTO responseDTO = new ServicesResponseDTO(
//                    service.getServiceName(),
//                    service.getPageName(),
//                    service.isActive()
//            );
//            responseDTOs.add(responseDTO);
//        }
//        return ResponseEntity.ok().body(responseDTOs);
//    }

//    @GetMapping("/servicesDetails/{serviceName}")
//    public ResponseEntity<ServiceDetailsResponseDTO> getServicesDetails(@PathVariable String serviceName) {
//        Services service = userServiceImpl.getServiceDetails(serviceName);
//        if (service == null) {
//            return ResponseEntity.notFound().build();
//        }
//        ServiceDetailsResponseDTO responseDTO = new ServiceDetailsResponseDTO();
//        responseDTO.setServiceName(service.getServiceName());
//        responseDTO.setDescription(service.getDescription());
//        responseDTO.setBeforeImageUrl(service.getBeforeImageUrl());
//        responseDTO.setAfterImageUrl(service.getAfterImageUrl());
//        return ResponseEntity.ok().body(responseDTO);
//    }


//    @PostMapping("/addServiceDetails")
//    public ResponseEntity<?> addServiceDetails(@RequestParam String serviceName,
//                                               @RequestParam(required = false) String description,
//                                               @RequestParam(required = false) MultipartFile beforeImage,
//                                               @RequestParam(required = false) MultipartFile afterImage) throws IOException {
//        userServiceImpl.addServiceDetails(serviceName, description, beforeImage, afterImage);
//        return ResponseEntity.ok().build();
//    }


//        @GetMapping("/getServiceDetails/{id}")
//        public ResponseEntity<List<ServiceDetailsRequestDTO>> getServicesDetails(@RequestParam String serviceName) {
//            List<ServiceDetailsRequestDTO> serviceDetails = userServiceImpl.getServicesDetails(serviceName);
//            return ResponseEntity.ok(serviceDetails);
//        }

//    @GetMapping("/getServiceDetails")
//    public ResponseEntity<ServiceDetailsRequestDTO> getServicesDetails(@RequestParam String serviceName) {
//        ServiceDetailsRequestDTO serviceDetails = userServiceImpl.getServicesDetails(serviceName);
//        return ResponseEntity.ok(serviceDetails);
//    }
//    @GetMapping("/getServicesDetails")
//    public ResponseEntity<List<ServiceDetailsRequestDTO>> getServicesDetails(@RequestParam String serviceName) {
//    List<ServiceDetailsRequestDTO> serviceDetails = UserServiceImpl.getServicesDetails(serviceName);
//    return ResponseEntity.ok(serviceDetails);
//}
}



