package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.DetailsDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;

import java.util.List;

public interface UserService {
    boolean disableAndEnableTheService(Long id, boolean isActive);
    List<ServicesRequestDTO> getServices();
    ServicesResponseDTO addService(ServicesRequestDTO servicesRequestDTO);
    ServicesResponseDTO updateService(Long id, ServicesRequestDTO dto);
    public List<DetailsDTO> getAllServicesDetailsWithName();
    void deleteService(Long id);
}
