package com.bezkoder.spring.jpa.h2.service;

<<<<<<< HEAD
import com.bezkoder.spring.jpa.h2.Entity.HomePage;
=======
import com.bezkoder.spring.jpa.h2.Entity.HomePageAboutUs;
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
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
<<<<<<< HEAD
    public HomePage getHomePageAboutUs(Long id) {
        Optional<HomePage> homePageAboutUsOptional = homePageAboutUsRepository.findById(id);
=======
    public HomePageAboutUs getHomePageAboutUs(Long id) {
        Optional<HomePageAboutUs> homePageAboutUsOptional = homePageAboutUsRepository.findById(id);
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
        return homePageAboutUsOptional.orElse(null);
    }

    @Override
<<<<<<< HEAD
    public HomePage updateHomePageAboutUs(Long id, HomePageAboutUsRequestDTO homePageAboutUsRequestDTO) {
        Optional<HomePage> homePageAboutUsOptional = homePageAboutUsRepository.findById(id);
        if (homePageAboutUsOptional.isPresent()) {
            HomePage homePage = homePageAboutUsOptional.get();
            homePage.setAboutusLink(homePageAboutUsRequestDTO.getLink());
            homePage.setAboutusDescription(homePageAboutUsRequestDTO.getDescription());
            return homePageAboutUsRepository.save(homePage);
        } else {
            HomePage homePage = new HomePage();
            homePage.setAboutusLink(homePageAboutUsRequestDTO.getLink());
            homePage.setAboutusDescription(homePageAboutUsRequestDTO.getDescription());
            return homePageAboutUsRepository.save(homePage);
=======
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
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d

        }
    }
    @Override
    public void updateAboutUsVideoUrl(Long id, String videoUrl) {
<<<<<<< HEAD
        HomePage homePage = homePageAboutUsRepository.findById(id).orElse(null);
        if (homePage != null) {
            homePage.setAboutusVideoUrl(videoUrl);
            homePageAboutUsRepository.save(homePage);
=======
        HomePageAboutUs homePageAboutUs = homePageAboutUsRepository.findById(id).orElse(null);
        if (homePageAboutUs != null) {
            homePageAboutUs.setVideoUrl(videoUrl);
            homePageAboutUsRepository.save(homePageAboutUs);
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
        }
    }

    @Override
    public String getAboutUsVideoUrl(Long id) {
<<<<<<< HEAD
        HomePage homePage = homePageAboutUsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AboutUs not found for id: " + id));
        return homePage.getAboutusVideoUrl();
=======
        HomePageAboutUs homePageAboutUs = homePageAboutUsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AboutUs not found for id: " + id));
        return homePageAboutUs.getVideoUrl();
>>>>>>> 9a3db2c4babd7a82f6857ad20d5dfb4517aaf88d
    }

}
