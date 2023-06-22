package com.bezkoder.spring.jpa.h2.service;


import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.PortfolioResponse;
import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServicesResponseDTO;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.ServicesMapper;
import com.bezkoder.spring.jpa.h2.repository.PortfolioRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Override
    public List<ServicesRequestDTO> getServicesByPage(String pageName) {
        List<Services> serviceEntities = servicesRepository.findServiceByPage(pageName);
        List<ServicesRequestDTO> services = serviceEntities.stream()
                .map(servicesEntity -> new ServicesRequestDTO(servicesEntity.getId(), servicesEntity.getServiceName(), servicesEntity.getPageName(), servicesEntity.isActive()))
                .collect(Collectors.toList());
        return services;
    }

    public ServicesResponseDTO getServiceById(Long id) {
        Services service = servicesRepository.findById(id)
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
            throw new GenericException(HttpStatus.NOT_FOUND," Service not found for id: " +id,"Incorrect id");
        }
    }

    public void deleteService(Long id) {
        Optional<Services> existingServices = servicesRepository.findById(id);
        if (existingServices.isPresent()) {
            servicesRepository.deleteById(id);
        } else {
            throw new GenericException(HttpStatus.NOT_FOUND," Service not found for id: " +id,"Incorrect id");
        }
    }


    public String uploadImages(Long id, MultipartFile[] images) throws IOException {
        Services service = servicesRepository.findById(id).orElse(null);

        if (service == null) {
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

    public List<PortfolioResponse> getImagesByServiceId(Long serviceId) {
        Services service = servicesRepository.findById(serviceId).orElse(null);

        if (service == null) {
            throw new GenericException(HttpStatus.NOT_FOUND, "Service not found by id " + serviceId, "Incorrect id");
        }

        List<Portfolio> portfolios = service.getPortfolios();
        List<PortfolioResponse> portfolioResponses = new ArrayList<>();

        for (Portfolio portfolio : portfolios) {
            PortfolioResponse response = new PortfolioResponse(portfolio.getId(), portfolio.getImageUrl());
            portfolioResponses.add(response);
        }

        return portfolioResponses;
    }
    public void updateImageByPortfolioId(Long portfolioId, MultipartFile image) throws IOException {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElse(null);

        if (portfolio == null) {
            throw new GenericException(HttpStatus.NOT_FOUND, "Portfolio not found by id " + portfolioId, "Incorrect id");
        }
        deleteImage(portfolio.getImageUrl());
        String newImageUrl = saveImage(image);
        portfolio.setImageUrl(newImageUrl);
        portfolioRepository.save(portfolio);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path uploadPath = Paths.get("uploads/projectimage");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = image.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        String relativePath = "/projectimage/" + uploadPath.relativize(filePath).toString();
        return relativePath;
    }
    private void deleteImage(String imageUrl) {
        if (imageUrl != null) {
            String filePath = "uploads/projectimage" + imageUrl.substring(imageUrl.lastIndexOf('/'));
            Path deletePath = Paths.get(filePath);
            try {
                Files.deleteIfExists(deletePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


