package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceWithDetailDTO;

import java.util.List;

public interface ServicesDetailsService {

    public ServiceDetailsDTO getServiceDetailsByServiceId(Long serviceId);


    public ServiceDetailsDTO updateServicesDetails(Long id, ServiceDetailsDTO servicesDetailsDTO);

    public ServiceDetailsDTO addServiceDetails(ServiceDetailsDTO serviceDetailsDTO, UserDetailsImpl userDetails);
    public List<ServiceWithDetailDTO> getAllServicesDetailsWithName();
}
