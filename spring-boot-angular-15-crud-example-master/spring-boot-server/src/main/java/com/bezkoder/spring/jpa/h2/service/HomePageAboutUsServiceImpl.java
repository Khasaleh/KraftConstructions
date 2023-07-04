package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.BannerRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.BannerResponseDTO;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceHomePageResponseDto;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.BannerMapper;
import com.bezkoder.spring.jpa.h2.mapper.HomePageAboutUsMapper;
import com.bezkoder.spring.jpa.h2.repository.HomePageRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesDetailsRepository;
import com.bezkoder.spring.jpa.h2.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    @Autowired
    private BannerMapper bannerMapper;

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
            homePage.setAboutusLink(homePageAboutUsRequestDTO.getAboutusLink());
            homePage.setAboutusDescription(homePageAboutUsRequestDTO.getAboutusDescription());
            return homePageRepository.save(homePage);
        } else {
            HomePage homePage = new HomePage();
            homePage.setAboutusLink(homePageAboutUsRequestDTO.getAboutusLink());
            homePage.setAboutusDescription(homePageAboutUsRequestDTO.getAboutusDescription());
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
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"AboutUs video not found for id: " + id,"Incorrect id"));
        return homePage.getAboutusVideoUrl();
    }

    public String addServiceToHomePage(Long id, List<Long> serviceIds) {
        if (serviceIds.size() != 3) {
            throw new GenericException(HttpStatus.CONFLICT, "Invalid number of service IDs provided: " + id," Exactly 3 service IDs are required.");
        }

        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Home page not found for id: " + id, "Incorrect id"));


        homePage.getServices().stream().forEach(s -> {
            s.setHomePage(null);
            servicesRepository.save(s);
        });
        homePage.getServices().clear();

        homePageRepository.save(homePage);
        for (Long sId : serviceIds) {
            Services service = servicesRepository.findById(sId).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Service not found for id: " + id, "Incorrect id"));
            if (Objects.nonNull(service)) {
                homePage.getServices().add(service);
                service.setHomePage(homePage);
                servicesRepository.save(service);
            }
        }

        homePageRepository.save(homePage);

        return "Service added to Homepage successfully";
    }


    public List<ServiceHomePageResponseDto> getServicesByHomePageId(Long id) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND," home page not found for id: " + id,"Incorrect id"));
        List<ServiceHomePageResponseDto> serviceDTOs = new ArrayList<>();
        for (Services service : homePage.getServices()) {
            ServiceHomePageResponseDto serviceDTO = new ServiceHomePageResponseDto();
            serviceDTO.setId(service.getId());
            serviceDTO.setServiceName(service.getServiceName());
            serviceDTO.setPageName(service.getPageName());
            serviceDTO.setAfterImageUrl(Objects.isNull(service.getServiceDetails()) ? "" :service.getServiceDetails().getAfterImageUrl());
            serviceDTO.setDescription(Objects.isNull(service.getServiceDetails()) ? "" :service.getServiceDetails().getDescription());
            serviceDTOs.add(serviceDTO);
        }

        return serviceDTOs;
    }
    @Override
    public HomePage updateBanner(Long id, BannerRequestDTO bannerRequestDTO) {
        Optional<HomePage> homePageOptional = homePageRepository.findById(id);
        if (homePageOptional.isPresent()) {
            HomePage homePage = homePageOptional.get();
            bannerMapper.mapToEntity(bannerRequestDTO, homePage);
            return homePageRepository.save(homePage);
        } else {
            HomePage homePage = new HomePage();
            bannerMapper.mapToEntity(bannerRequestDTO, homePage);
            return homePageRepository.save(homePage);
        }
    }
    @Override
    public BannerResponseDTO getBanner(Long id) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Banner not found for id: " + id, "Incorrect id"));
        return bannerMapper.mapToResponseDTO(homePage);
    }
    public void updateLinkStatus(Long id, boolean linkStatus) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "HomePage not found for id: " + id, "Incorrect id"));

        boolean updatedLinkStatus = !homePage.isLinkStatus(); // Toggle the linkStatus value

        homePage.setLinkStatus(updatedLinkStatus);
        homePageRepository.save(homePage);
    }
    @Override
    public boolean updateLinkStatus(Long id) {
        HomePage homePage = homePageRepository.findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "HomePage not found for id: " + id, "Incorrect id"));

        boolean updatedLinkStatus = !homePage.isLinkStatus(); // Toggle the linkStatus value

        homePage.setLinkStatus(updatedLinkStatus);
        homePageRepository.save(homePage);

        return updatedLinkStatus;
    }
}
