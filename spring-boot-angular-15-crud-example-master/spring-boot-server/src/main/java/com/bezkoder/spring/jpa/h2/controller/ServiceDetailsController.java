package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsResponseDTO;
import com.bezkoder.spring.jpa.h2.entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.service.ServDetailInfoImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ServiceDetailsController {

    @GetMapping("/getServiceDetails/{id}")
    public ResponseEntity<ServiceDetailsResponseDTO> getServiceDetailsById(@PathVariable Long id) {
        ServiceDetails serviceDetails = ServDetailInfoImpl.getServiceDetailsById(id);
        if (serviceDetails == null) {
            return ResponseEntity.notFound().build();
        }
        ServiceDetailsResponseDTO responseDTO = new ServiceDetailsResponseDTO();
        responseDTO.setServiceName(serviceDetails.getServiceName());
        responseDTO.setDescription(serviceDetails.getDescription());
        responseDTO.setBeforeImageUrl(serviceDetails.getBeforeImageUrl());
        responseDTO.setAfterImageUrl(serviceDetails.getAfterImageUrl());
        responseDTO.setAddPortfolio(serviceDetails.getAddPortfolio());

        return ResponseEntity.ok().build();
    }
}



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
//        ServDetailInfoImpl.addServiceDetails(serviceName, description, beforeImage, afterImage);
//        return ResponseEntity.ok().build();
//    }


