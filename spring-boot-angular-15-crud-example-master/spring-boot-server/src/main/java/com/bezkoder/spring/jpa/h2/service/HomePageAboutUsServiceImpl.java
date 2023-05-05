package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
import com.bezkoder.spring.jpa.h2.dto.HomePageAboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.mapper.HomePageAboutUsMapper;
import com.bezkoder.spring.jpa.h2.repository.HomePageAboutUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HomePageAboutUsServiceImpl implements HomePageAboutUsService {

    @Autowired
    private HomePageAboutUsRepository homePageAboutUsRepository;

    @Autowired
    private HomePageAboutUsMapper homePageAboutUsMapper;

    @Override
    public HomePageAboutUs getHomePageAboutUs(Long id) {
        Optional<HomePageAboutUs> homePageAboutUsOptional = homePageAboutUsRepository.findById(id);
        return homePageAboutUsOptional.orElse(null);
    }

    @Override
    public HomePageAboutUs updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO) {
        Optional<HomePageAboutUs> homePageAboutUsOptional = homePageAboutUsRepository.findById(id);
        if (homePageAboutUsOptional.isPresent()) {
            HomePageAboutUs homePageAboutUs = homePageAboutUsOptional.get();
            homePageAboutUs.setLink(homePageAboutUsRequestDTO.getLink());
            homePageAboutUs.setDescription(homePageAboutUsRequestDTO.getDescription());
            return homePageAboutUsRepository.save(homePageAboutUs);
        } else {
            HomePageAboutUs homePageAboutUs = new HomePageAboutUs();
            homePageAboutUs.setLink(homePageAboutUsRequestDTO.getLink());
            homePageAboutUs.setDescription(homePageAboutUsRequestDTO.getDescription());
            return homePageAboutUsRepository.save(homePageAboutUs);

        }
    }
    @Override
    public void updateAboutUsVideoUrl(Long id, String videoUrl) {
        HomePageAboutUs homePageAboutUs = homePageAboutUsRepository.findById(id).orElse(null);
        if (homePageAboutUs != null) {
            homePageAboutUs.setVideoUrl(videoUrl);
            homePageAboutUsRepository.save(homePageAboutUs);
        }
    }

    @Override
    public String getAboutUsVideoUrl(Long id) {
        HomePageAboutUs homePageAboutUs = homePageAboutUsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AboutUs not found for id: " + id));
        return homePageAboutUs.getVideoUrl();
    }

}
