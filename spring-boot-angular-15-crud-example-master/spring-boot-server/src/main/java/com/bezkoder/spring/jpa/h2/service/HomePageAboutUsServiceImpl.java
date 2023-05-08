package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePage;
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
    public HomePage getHomePageAboutUs(Long id) {
        Optional<HomePage> homePageAboutUsOptional = homePageAboutUsRepository.findById(id);
        return homePageAboutUsOptional.orElse(null);
    }

    @Override
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

        }
    }
    @Override
    public void updateAboutUsVideoUrl(Long id, String videoUrl) {
        HomePage homePage = homePageAboutUsRepository.findById(id).orElse(null);
        if (homePage != null) {
            homePage.setAboutusVideoUrl(videoUrl);
            homePageAboutUsRepository.save(homePage);
        }
    }

    @Override
    public String getAboutUsVideoUrl(Long id) {
        HomePage homePage = homePageAboutUsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AboutUs not found for id: " + id));
        return homePage.getAboutusVideoUrl();
    }

}
