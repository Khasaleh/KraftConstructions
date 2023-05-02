package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsDTO;

public interface ServicesDetailsService {

    public ServiceDetailsDTO getServiceDetailsById(Long id);
    public void deleteServiceDetails(Long id);

    public ServiceDetailsDTO updateServicesDetails(Long id, ServiceDetailsDTO servicesDetailsDTO);

    public ServiceDetailsDTO addServiceDetails(ServiceDetailsDTO serviceDetailsDTO, UserDetailsImpl userDetails);
}
