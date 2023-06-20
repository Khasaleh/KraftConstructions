package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;

import java.util.List;

public interface ServicesService {
    boolean disableAndEnableTheService(Long id, boolean isActive);
    List<ServicesRequestDTO> getServices();

    List<ServicesRequestDTO> getServicesByPage(String pageName);
    ServicesResponseDTO addService(ServicesRequestDTO servicesRequestDTO);
    ServicesResponseDTO updateService(Long id, ServicesRequestDTO dto);
    void deleteService(Long id);

}
