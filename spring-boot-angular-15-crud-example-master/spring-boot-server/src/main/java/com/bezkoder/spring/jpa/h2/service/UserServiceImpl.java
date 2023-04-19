package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.mapper.ServicesMapper;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    @Autowired
    private  ServicesRepository servicesRepository;

    @Autowired
    private ServicesMapper servicesMapper;



    public void addService(ServicesRequestDTO servicesRequestDTO) {
        Services serviceEntity = new Services(servicesRequestDTO.getServiceName(), servicesRequestDTO.getPageName(), servicesRequestDTO.isActive());
        servicesRepository.save(serviceEntity);
    }

    public List<ServicesRequestDTO> getServices() {
        List<Services> serviceEntities = servicesRepository.findAll();
        List<ServicesRequestDTO> services = serviceEntities.stream()
                .map(servicesEntity -> new ServicesRequestDTO(servicesEntity.getId(),servicesEntity.getServiceName(), servicesEntity.getPageName(), servicesEntity.isActive()))
                .collect(Collectors.toList());
        return services;
    }

    public void disableAndEnableTheService(String serviceName, boolean isActive) {
        Services services = servicesRepository.findByServiceName(serviceName)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        services.setActive(isActive);
        servicesRepository.save(services);
    }



}
