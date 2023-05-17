package com.bezkoder.spring.jpa.h2.service;


import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.ServicesMapper;
import com.bezkoder.spring.jpa.h2.repository.PortfolioRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
//                .orElseThrow(() -> new IllegalArgumentException("Service" + "id" + id));
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND," Service not found for id: " +id,"Incorrect id"));
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
//            throw new RuntimeException("Service Not Found");
            throw new GenericException(HttpStatus.NOT_FOUND," Service not found for id: " +id,"Incorrect id");
        }
    }

    public void deleteService(Long id) {
        Optional<Services> existingServices = servicesRepository.findById(id);
        if (existingServices.isPresent()) {
            servicesRepository.deleteById(id);
        } else {
//            throw new IllegalArgumentException("Service with ID " + id + " not found");
            throw new GenericException(HttpStatus.NOT_FOUND," Service not found for id: " +id,"Incorrect id");
        }
    }


    public String uploadImages(Long id, MultipartFile[] images) {
        Services service = servicesRepository.findById(id).orElse(null);

        if (service == null) {
          // throw new ObjectNotFoundException("Service not found", id.toString());
            throw new GenericException(HttpStatus.NOT_FOUND," Service not found by id" +id,"Incorrect id");
        }

        if (service.getServiceDetails().isAddPortfolio()) {
            for (MultipartFile image : images) {
                Portfolio portfolio = new Portfolio();
                String imageUrl = saveImage(image);
                portfolio.setImageUrl(imageUrl);
                portfolio.setServices(service);
                Portfolio savedPortfolio = portfolioRepository.save(portfolio);
                service.getPortfolios().add(savedPortfolio);
            }

            servicesRepository.save(service);
            return "Added Project Images Successfully";
        }
        return "Portfolio check is false so Project Image can't be added";
    }

    private String saveImage(MultipartFile image) {

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        try {
            File file = new File("uploads/projectImage/" + fileName);

            FileUtils.writeByteArrayToFile(file, image.getBytes());
            return file.getPath();

        } catch (IOException e) {
            throw new InvalidPathException("Could not store image " + fileName + ". Please try again!", e.getMessage());
        }
    }

}


