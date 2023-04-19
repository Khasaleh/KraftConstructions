package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.mapper.AboutUsMapper;
import com.bezkoder.spring.jpa.h2.repository.AboutUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AboutUsServiceImpl implements AboutUsService {

    @Autowired
    private AboutUsRepository aboutUsRepository;

    @Autowired
    private AboutUsMapper aboutUsMapper;

    @Override
    public AboutUs getAboutUs(Long id) {
        Optional<AboutUs> aboutUsOptional = aboutUsRepository.findById(id);
        return aboutUsOptional.orElse(null);
    }

    @Override
    public AboutUs updateAboutUs(Long id, AboutUsRequestDTO aboutUsRequestDto) {
        Optional<AboutUs> aboutUsOptional = aboutUsRepository.findById(id);
        if (aboutUsOptional.isPresent()) {
            AboutUs aboutUs = aboutUsOptional.get();
            aboutUs.setTitle(aboutUsRequestDto.getTitle());
            aboutUs.setDescription(aboutUsRequestDto.getDescription());
            return aboutUsRepository.save(aboutUs);
        } else {
            throw new RuntimeException("AboutUs Not Found");
        }
    }
}