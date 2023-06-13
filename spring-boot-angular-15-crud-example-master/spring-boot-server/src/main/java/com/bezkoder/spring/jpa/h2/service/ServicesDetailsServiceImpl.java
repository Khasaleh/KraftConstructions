package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.Entity.User;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsResponseDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceWithDetailDTO;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.ServiceDetailsMapper;
import com.bezkoder.spring.jpa.h2.mapper.ServicesMapper;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServicesDetailsServiceImpl implements ServicesDetailsService {

    private static final String UPLOAD_DIRECTORY = "uploads/services";

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
    public ServiceDetailsResponseDTO addServiceDetails(ServiceDetailsRequestDTO serviceDetailsRequestDTO, UserDetailsImpl userDetails) throws IOException {
        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new GenericException(HttpStatus.NO_CONTENT,"User Not Found","Author can't be determined"));
        Services service = servicesRepository.findById(serviceDetailsRequestDTO.getServiceId())
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Service not found" + serviceDetailsRequestDTO.getServiceId(), "Incorrect id"));

        String beforeImageFileName = saveFile(serviceDetailsRequestDTO.getBeforeImage());
        String afterImageFileName = saveFile(serviceDetailsRequestDTO.getAfterImage());

        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setServices(service);
        serviceDetails.setBeforeImageUrl(beforeImageFileName);
        serviceDetails.setAfterImageUrl(afterImageFileName);
        serviceDetails.setBeforeImageTitle(serviceDetailsRequestDTO.getBeforeImageTitle());
        serviceDetails.setAfterImageTitle(serviceDetailsRequestDTO.getAfterImageTitle());
        serviceDetails.setDescription(serviceDetailsRequestDTO.getDescription());
        serviceDetails.setAddPortfolio(serviceDetailsRequestDTO.isAddPortfolio());
        serviceDetails.setAuthor(user.getUsername());
        serviceDetails.setUpdateDate(LocalDateTime.now());

        serviceDetails = servicesDetailsRepository.save(serviceDetails);
        return serviceDetailsMapper.toDto(serviceDetails);
    }

    @Override
    public ServiceDetailsResponseDTO getServiceDetailsByServiceId(Long serviceId) {
        Optional<Services> optionalServices = servicesRepository.findById(serviceId);
        if (optionalServices.isPresent()) {
            return serviceDetailsMapper.toDto(optionalServices.get().getServiceDetails());
        }
        return null;
    }

    @Override
    public ServiceDetailsResponseDTO updateServicesDetails(Long id, ServiceDetailsRequestDTO servicesDetailsRequestDTO,UserDetailsImpl userDetails) throws IOException {
        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new GenericException(HttpStatus.NO_CONTENT,"User Not Found","Author can't be determined"));
        Optional<ServiceDetails> servicesDetailsOptional = servicesDetailsRepository.findById(id);
        if (servicesDetailsOptional.isPresent()) {
            ServiceDetails servicesDetails = servicesDetailsOptional.get();

            if (servicesDetailsRequestDTO.getBeforeImage() != null) {
                deleteFile(servicesDetails.getBeforeImageUrl());
                String beforeImageFileName = saveFile(servicesDetailsRequestDTO.getBeforeImage());
                servicesDetails.setBeforeImageUrl(beforeImageFileName);
            }

            if (servicesDetailsRequestDTO.getAfterImage() != null) {
                deleteFile(servicesDetails.getAfterImageUrl());
                String afterImageFileName = saveFile(servicesDetailsRequestDTO.getAfterImage());
                servicesDetails.setAfterImageUrl(afterImageFileName);
            }

            servicesDetails.setUpdateDate(LocalDateTime.now());
            servicesDetails.setBeforeImageTitle(servicesDetailsRequestDTO.getBeforeImageTitle());
            servicesDetails.setAfterImageTitle(servicesDetailsRequestDTO.getAfterImageTitle());
            servicesDetails.setDescription(servicesDetailsRequestDTO.getDescription());
            servicesDetails.setAddPortfolio(servicesDetailsRequestDTO.isAddPortfolio());
            servicesDetails.setAuthor(user.getUsername());
            servicesDetails.setUpdateDate(LocalDateTime.now());

            Services services = servicesDetails.getServices();
            servicesRepository.save(services);
            return convertToDTO(servicesDetailsRepository.save(servicesDetails));
        } else {
            return null;
        }
    }

    private ServiceDetailsResponseDTO convertToDTO(ServiceDetails servicesDetails) {
        ServiceDetailsResponseDTO servicesDetailsDTO = new ServiceDetailsResponseDTO();
        servicesDetailsDTO.setId(servicesDetails.getId());
        servicesDetailsDTO.setServiceId(servicesDetails.getServices().getId());
        servicesDetailsDTO.setAddPortfolio(servicesDetails.isAddPortfolio());
        servicesDetailsDTO.setDescription(servicesDetails.getDescription());
        servicesDetailsDTO.setBeforeImageTitle(servicesDetails.getBeforeImageTitle());
        servicesDetailsDTO.setAfterImageTitle(servicesDetails.getAfterImageTitle());
        servicesDetailsDTO.setAfterImageUrl(servicesDetails.getAfterImageUrl());
        servicesDetailsDTO.setBeforeImageUrl(servicesDetails.getBeforeImageUrl());
        servicesDetailsDTO.setUpdateDate(servicesDetails.getUpdateDate());
        servicesDetailsDTO.setAuthor(servicesDetails.getAuthor());

        return servicesDetailsDTO;
    }

    @Override
    public List<ServiceWithDetailDTO> getAllServicesDetailsWithName() {
        List<Services> services = servicesRepository.findAll();
        List<ServiceWithDetailDTO> serviceDTOs = new ArrayList<>();
        for (Services service : services) {
            ServiceWithDetailDTO serviceDTO = ServiceWithDetailDTO.builder()
                    .serviceName(service.getServiceName())
                    .description(Objects.isNull(service.getServiceDetails()) ? "" : service.getServiceDetails().getDescription())
                    .author(Objects.isNull(service.getServiceDetails()) ? "" : service.getServiceDetails().getAuthor())
                    .updateDate(Objects.isNull(service.getServiceDetails()) ? null : service.getServiceDetails().getUpdateDate())
                    .build();
            serviceDTOs.add(serviceDTO);
        }
        return serviceDTOs;
    }

    private String saveFile(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = image.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        String relativePath = "/services/" + uploadPath.relativize(filePath).toString();
        return relativePath;
    }

    private void deleteFile(String imageUrl) {
        if (imageUrl != null) {
            String filePath = UPLOAD_DIRECTORY + imageUrl.substring(imageUrl.lastIndexOf('/'));
            Path deletePath = Paths.get(filePath);
            try {
                Files.deleteIfExists(deletePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
