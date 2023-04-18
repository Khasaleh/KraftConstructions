package com.bezkoder.spring.jpa.h2.service;

//import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;

import com.bezkoder.spring.jpa.h2.dto.ServicesRequestDTO;
import com.bezkoder.spring.jpa.h2.entity.Services;
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

//    @Autowired
//    private ServicesDetailsMapper servicesDetailsMapper;

    public void addService(ServicesRequestDTO servicesRequestDTO) {
        Services serviceEntity = new Services(servicesRequestDTO.getServiceName(), servicesRequestDTO.getPageName(), servicesRequestDTO.isActive());
        servicesRepository.save(serviceEntity);
    }

    public List<ServicesRequestDTO> getServices() {
        List<Services> serviceEntities = servicesRepository.findAll();
        List<ServicesRequestDTO> services = serviceEntities.stream()
                .map(servicesEntity -> new ServicesRequestDTO(servicesEntity.getServiceName(), servicesEntity.getPageName(), servicesEntity.isActive()))
                .collect(Collectors.toList());
        return services;
    }

    public void disableAndEnableTheService(String serviceName, boolean isActive) {
        Services services = servicesRepository.findByServiceName(serviceName)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        services.setActive(isActive);
        servicesRepository.save(services);
    }

//    public void addServiceDetails(String serviceName, String description, MultipartFile beforeImage, MultipartFile afterImage) throws IOException {
//        Services serviceEntity = servicesRepository.findByServiceName(serviceName)
//                .orElseThrow(() -> new RuntimeException("Service not found"));
//
//        String beforeImageUrl = saveImage(beforeImage);
//        String afterImageUrl = saveImage(afterImage);
//
//        serviceEntity.setDescription(description);
//        serviceEntity.setBeforeImageUrl(beforeImageUrl);
//        serviceEntity.setAfterImageUrl(afterImageUrl);
//        servicesRepository.save(serviceEntity);
//
//    }

//    private String saveImage(MultipartFile image) throws IOException {
//        String fileName = UUID.randomUUID().toString();
//        String fileExtension = FilenameUtils.getExtension(image.getOriginalFilename());
//        String filePath = "images/" + fileName + "." + fileExtension;
//
//        File file = new File(filePath);
//        FileUtils.writeByteArrayToFile(file, image.getBytes());
//
//        return filePath;
//    }





//    public Optional<Services> getServiceDetails(String serviceName) {
//        Optional<Services> service = servicesRepository.findByServiceName(serviceName);
//        if (!service.isPresent()) {
//            throw new RuntimeException("service not found");
//        }
//
//        return service;
//    }

//    public  List<ServiceDetailsRequestDTO> getServicesDetails(String serviceName) {
//        List<Services> serviceEntities = servicesRepository.findAll();
//        List<ServiceDetailsRequestDTO> services = serviceEntities.stream()
//                .map(servicesEntity -> new ServiceDetailsRequestDTO(servicesEntity.getServiceName(), servicesEntity.getBeforeImageUrl(), servicesEntity.getAfterImageUrl(),servicesEntity.getDescription(),servicesEntity.getAddPortfolio()))
//                .collect(Collectors.toList());
//        return services;
//
//    }

//    public ServiceDetailsRequestDTO getServicesDetails(String serviceName) {
//        services
//        return new ServiceDetailsRequestDTO(serviceName, description, beforeImageUrl, afterImageUrl, addPortfolio);
//    }


}
