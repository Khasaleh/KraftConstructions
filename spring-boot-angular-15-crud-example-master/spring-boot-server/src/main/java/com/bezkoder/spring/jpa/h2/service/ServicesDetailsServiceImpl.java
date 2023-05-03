package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.Entity.User;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceWithDetailDTO;
import com.bezkoder.spring.jpa.h2.mapper.ServiceDetailsMapper;
import com.bezkoder.spring.jpa.h2.mapper.ServicesMapper;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ServicesDetailsServiceImpl implements ServicesDetailsService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesDetailsRepository servicesDetailsRepository;

    @Autowired
    private ServiceDetailsMapper serviceDetailsMapper;

    @Autowired
    private ServicesMapper servicesMapper;


    @Override
    public ServiceDetailsDTO addServiceDetails(ServiceDetailsDTO serviceDetailsDTO, UserDetailsImpl userDetails) {

        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException());
        Services service = servicesRepository.findById(serviceDetailsDTO.getServiceId()).orElseThrow(() -> new IllegalArgumentException("Service not found"+ serviceDetailsDTO.getServiceId()));
        serviceDetailsDTO.setAuthor(user.getUsername());
        ServiceDetails serviceDetails = serviceDetailsMapper.toEntity(serviceDetailsDTO, service);
        serviceDetails = servicesDetailsRepository.save(serviceDetails);
        return serviceDetailsMapper.toDto(serviceDetails);
    }


    @Override
    public ServiceDetailsDTO getServiceDetailsByServiceId(Long serviceId) {
        Optional<Services> optionalServices = servicesRepository.findById(serviceId);
        if (optionalServices.isPresent()){
            return serviceDetailsMapper.toDto(optionalServices.get().getServiceDetails());
        }
        return null;
    }



    @Override
    public ServiceDetailsDTO updateServicesDetails(Long id, ServiceDetailsDTO servicesDetailsDTO) {
        Optional<ServiceDetails> servicesDetailsOptional = servicesDetailsRepository.findById(id);
        if (servicesDetailsOptional.isPresent()) {
            ServiceDetails servicesDetails = servicesDetailsOptional.get();
            servicesDetails.setUpdateDate(servicesDetailsDTO.getUpdateDate());
            servicesDetails.setBeforeImageUrl(servicesDetailsDTO.getBeforeImageUrl());
            servicesDetails.setAuthor(servicesDetailsDTO.getAuthor());
            servicesDetails.setDescription(servicesDetailsDTO.getDescription());
            servicesDetails.setAfterImageUrl(servicesDetailsDTO.getAfterImageUrl());
            servicesDetails.setAddPortfolio(servicesDetailsDTO.isAddPortfolio());

            Services services = servicesDetails.getServices();
            servicesRepository.save(services);
            return convertToDTO(servicesDetailsRepository.save(servicesDetails));
        } else {
            return null;
        }
    }

    private ServiceDetailsDTO convertToDTO(ServiceDetails servicesDetails) {
        ServiceDetailsDTO servicesDetailsDTO = new ServiceDetailsDTO();
        servicesDetailsDTO.setId(servicesDetails.getId());
        servicesDetailsDTO.setServiceId(servicesDetails.getServices().getId());
        servicesDetailsDTO.setAddPortfolio(servicesDetails.isAddPortfolio());
        servicesDetailsDTO.setDescription(servicesDetails.getDescription());
        servicesDetailsDTO.setAfterImageUrl(servicesDetails.getAfterImageUrl());
        servicesDetailsDTO.setBeforeImageUrl(servicesDetails.getBeforeImageUrl());
        servicesDetailsDTO.setUpdateDate(servicesDetails.getUpdateDate());
        servicesDetailsDTO.setAuthor(servicesDetails.getAuthor());

        return servicesDetailsDTO;
    }




    @Override
    public List<ServiceWithDetailDTO> getAllServicesDetailsWithName() {
        List<Services> services= servicesRepository.findAll();
        List<ServiceWithDetailDTO> serviceDTOs = new ArrayList<>();
        for (Services service : services) {
            ServiceWithDetailDTO serviceDTO = ServiceWithDetailDTO.builder().serviceName(service.getServiceName())
                            .description(service.getServiceDetails().getDescription()).author(service.getServiceDetails().getAuthor())
                            .updateDate(service.getServiceDetails().getUpdateDate()).build();
            serviceDTOs.add(serviceDTO);
        }
        return serviceDTOs;
    }
}