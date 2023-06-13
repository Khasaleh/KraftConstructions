package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsResponseDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceWithDetailDTO;

import java.io.IOException;
import java.util.List;

public interface ServicesDetailsService {

    public ServiceDetailsResponseDTO getServiceDetailsByServiceId(Long serviceId);


    public ServiceDetailsResponseDTO updateServicesDetails(Long id, ServiceDetailsRequestDTO serviceDetailsRequestDTO, UserDetailsImpl userDetails) throws IOException;

    public ServiceDetailsResponseDTO addServiceDetails(ServiceDetailsRequestDTO serviceDetailsRequestDTO, UserDetailsImpl userDetails) throws IOException;
    public List<ServiceWithDetailDTO> getAllServicesDetailsWithName();
}
