package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePageSlider;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.HomePageSliderMapper;
import com.bezkoder.spring.jpa.h2.repository.HomePageSliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomePageSliderServiceImpl implements HomePageSliderService{
    @Autowired
    private HomePageSliderRepository imageRepository;
    @Autowired
    private HomePageSliderMapper imageMapper;
    @Override
    public List<HomePageSliderResponseDto> getAllSliderImages() {
        List<HomePageSlider> images = imageRepository.findAll();
        return images.stream()
                .map(imageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HomePageSliderResponseDto createOrUpdateSliderImage(HomePageSliderResponseDto dto) {
        if (dto.getId() >= 1 && dto.getId() <= 5) {
            HomePageSlider entity = imageRepository.findById(dto.getId()).orElse(new HomePageSlider());
            imageMapper.updateEntity(entity, dto);
            HomePageSlider savedImage = imageRepository.save(entity);
            return imageMapper.toDTO(savedImage);
        } else {
            throw new IllegalArgumentException("Invalid image ID. Allowed range: 1-5.");
        }
    }

    @Override
    public void deleteSliderImage(Long id) {
        HomePageSlider entity = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slider image not found with id: " + id));
        imageRepository.delete(entity);
    }
}
