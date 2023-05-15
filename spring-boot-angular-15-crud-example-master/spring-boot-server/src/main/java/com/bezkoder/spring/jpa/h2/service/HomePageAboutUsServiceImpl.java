package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceHomePageResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.HomePageAboutUsMapper;
import com.bezkoder.spring.jpa.h2.repository.HomePageRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomePageAboutUsServiceImpl implements HomePageAboutUsService {

    @Autowired
    private HomePageRepository homePageRepository;

    @Autowired
    private HomePageAboutUsMapper homePageAboutUsMapper;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServicesDetailsRepository servicesDetailsRepository;

    @Override
    public HomePage getHomePageAboutUs(Long id) {
        Optional<HomePage> homePageAboutUsOptional = homePageRepository.findById(id);
        return homePageAboutUsOptional.orElse(null);
    }

    @Override
    public HomePage updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO) {
        Optional<HomePage> homePageAboutUsOptional = homePageRepository.findById(id);
        if (homePageAboutUsOptional.isPresent()) {
            HomePage homePage = homePageAboutUsOptional.get();
            homePage.setAboutusLink(homePageAboutUsRequestDTO.getLink());
            homePage.setAboutusDescription(homePageAboutUsRequestDTO.getDescription());
            return homePageRepository.save(homePage);
        } else {
            HomePage homePage = new HomePage();
            homePage.setAboutusLink(homePageAboutUsRequestDTO.getLink());
            homePage.setAboutusDescription(homePageAboutUsRequestDTO.getDescription());
            return homePageRepository.save(homePage);

        }
    }

    @Override
    public void updateAboutUsVideoUrl(Long id, String videoUrl) {
        HomePage homePage = homePageRepository.findById(id).orElse(null);
        if (homePage != null) {
            homePage.setAboutusVideoUrl(videoUrl);
            homePageRepository.save(homePage);
        }
    }

    @Override
    public String getAboutUsVideoUrl(Long id) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AboutUs not found for id: " + id));
        return homePage.getAboutusVideoUrl();
    }

    public String addServiceToHomePage(Long id, List<Long> serviceIds) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid home page id: " + id));
        serviceIds.stream().forEach(sId -> {
            Services service = servicesRepository.findById(sId).orElse(null);
            if (Objects.nonNull(service)) {
                homePage.getServices().add(service);
                service.setHomePage(homePage);
                servicesRepository.save(service);
            }
        });
        homePageRepository.save(homePage);

        return "Service added to Homepage successfully";
    }

    public List<ServiceHomePageResponseDto> getServicesByHomePageId(Long id) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid home page id: " + id));
        List<ServiceHomePageResponseDto> serviceDTOs = new ArrayList<>();
        for (Services service : homePage.getServices()) {
            ServiceHomePageResponseDto serviceDTO = new ServiceHomePageResponseDto();
            serviceDTO.setId(service.getId());
            serviceDTO.setServiceName(service.getServiceName());
            serviceDTO.setAfterImageUrl(service.getServiceDetails().getAfterImageUrl());
            serviceDTO.setDescription(service.getServiceDetails().getDescription());
            serviceDTOs.add(serviceDTO);
        }

        return serviceDTOs;
    }

}
