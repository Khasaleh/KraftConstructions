package com.bezkoder.spring.jpa.h2.service;


import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioServiceRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;
import com.bezkoder.spring.jpa.h2.mapper.ServicesMapper;
import com.bezkoder.spring.jpa.h2.repository.PortfolioRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private ServicesDetailsRepository servicesDetailsRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;


    public ServicesResponseDTO addService(ServicesRequestDTO servicesRequestDTO) {
        Services serviceEntity = new Services(servicesRequestDTO.getServiceName(), servicesRequestDTO.getPageName(), servicesRequestDTO.isActive());
        serviceEntity = servicesRepository.save(serviceEntity);
        return servicesMapper.toDto(serviceEntity);
    }

    public List<ServicesRequestDTO> getServices() {
        List<Services> serviceEntities = servicesRepository.findAll();
        List<ServicesRequestDTO> services = serviceEntities.stream()
                .map(servicesEntity -> new ServicesRequestDTO(servicesEntity.getId(), servicesEntity.getServiceName(), servicesEntity.getPageName(), servicesEntity.isActive()))
                .collect(Collectors.toList());
        return services;
    }

    public ServicesResponseDTO getServiceById(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service" + "id" + id));
        return servicesMapper.toDto(service);
    }

    public boolean disableAndEnableTheService(Long id, boolean isActive) {
        Optional<Services> optionalService = servicesRepository.findById(id);
        if (optionalService.isPresent()) {
            Services services = optionalService.get();
            services.setActive(isActive);
            servicesRepository.save(services);
            return true;
        } else {
            return false;
        }
    }

    public ServicesResponseDTO updateService(Long id, ServicesRequestDTO dto) {
        Optional<Services> optionalService = servicesRepository.findById(id);
        if (optionalService.isPresent()) {
            Services services = optionalService.get();
            services.setServiceName(dto.getServiceName());
            services.setPageName(dto.getPageName());
            services.setActive(dto.isActive());
            services = servicesRepository.save(services);
            return servicesMapper.toDto(services);
        } else {
            throw new RuntimeException("Service Not Found");
        }
    }

    public void deleteService(Long id) {
        Optional<Services> existingServices = servicesRepository.findById(id);
        if (existingServices.isPresent()) {
            servicesRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Service with ID " + id + " not found");
        }
    }

    @Override
    public String addPortfolioToService(Long id, PortfolioServiceRequestDTO portfolioServiceRequestDTO) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service" + "id" + id));
        if(service.getServiceDetails().isAddPortfolio()){
            portfolioServiceRequestDTO.getPortfoliosId().stream().forEach(p -> {
                Optional<Portfolio> portfolio= portfolioRepository.findById(p);
                if(portfolio.isPresent()){
                    service.getPortfolios().add(portfolio.get());
                }
            });
            service.setPortfolioColumns(portfolioServiceRequestDTO.getPortfolioColumn());
            servicesRepository.save(service);
            return "Portfolio Added to the ServicePage Successfully";
        }
        return "Portfolio can't be added because this service doesn't allow to add portfolio";
    }


}
