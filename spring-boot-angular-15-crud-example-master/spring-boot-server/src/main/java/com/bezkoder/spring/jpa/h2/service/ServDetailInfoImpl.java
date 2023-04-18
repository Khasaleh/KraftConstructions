package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;
import com.bezkoder.spring.jpa.h2.entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.mapper.ServicesDetailsMapper;
import com.bezkoder.spring.jpa.h2.repository.ServDetailRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ServDetailInfoImpl implements ServDetailInfo {

    @Autowired
    private ServDetailRepository servDetailRepository;

    @Autowired
    private ServicesDetailsMapper servicesDetailsMapper;

    public void addServiceDetails(Long id, String serviceName, String description, MultipartFile beforeImage, MultipartFile afterImage, Boolean addPortfolio) throws IOException {
        ServiceDetails serviceEntity = ServDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        String beforeImageUrl = saveImage(beforeImage);
        String afterImageUrl = saveImage(afterImage);

        serviceEntity.setDescription(description);
        serviceEntity.setBeforeImageUrl(beforeImageUrl);
        serviceEntity.setAfterImageUrl(afterImageUrl);

    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String fileExtension = FilenameUtils.getExtension(image.getOriginalFilename());
        String filePath = "images/" + fileName + "." + fileExtension;

        File file = new File(filePath);
        FileUtils.writeByteArrayToFile(file, image.getBytes());

        return filePath;
    }


    @Override
    public ServiceDetailsRequestDTO getServiceDetailsById(Long id) {
        ServiceDetails service = ServDetailRepository.findById(id).orElse(null);
        if (service == null) {
            // handle case when service is not found, e.g. throw an exception or return null
            return null;
        }
        ServiceDetailsRequestDTO serviceDetails = new ServiceDetailsRequestDTO();
        serviceDetails.setServiceName(service.getServiceName());
        serviceDetails.setDescription(service.getDescription());
        serviceDetails.setBeforeImageUrl(service.getBeforeImageUrl());
        serviceDetails.setAfterImageUrl(service.getAfterImageUrl());
        serviceDetails.setAddPortfolio(service.getAddPortfolio());

        return serviceDetails;
    }
}

