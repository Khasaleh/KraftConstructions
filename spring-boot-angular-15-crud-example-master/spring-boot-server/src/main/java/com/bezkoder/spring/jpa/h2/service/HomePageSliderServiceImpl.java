package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePageSlider;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.HomePageSliderMapper;
import com.bezkoder.spring.jpa.h2.repository.HomePageSliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomePageSliderServiceImpl implements HomePageSliderService{
    @Autowired
    private HomePageSliderRepository imageRepository;
    @Autowired
    private HomePageSliderMapper imageMapper;
    @Override
    public HomePageSliderResponseDto saveOrUpdateSliderImage(HomePageSliderRequestDto requestDTO) throws IOException {
        HomePageSlider entity = imageMapper.toEntity(requestDTO);
        MultipartFile imageFile = requestDTO.getImage();
        String imageUrl = saveImage(imageFile);

        entity.setImageUrl(imageUrl);
        HomePageSlider savedEntity = imageRepository.save(entity);
        return imageMapper.toResponseDTO(savedEntity);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path uploadPath = Paths.get("uploads/homepagebanner");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = image.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        String relativePath = "/homepagebanner/" + uploadPath.relativize(filePath).toString();
        return relativePath;
    }
    @Override
    public HomePageSliderResponseDto getSliderImageById(Long id) {
        Optional<HomePageSlider> optionalSliderImage = imageRepository.findById(id);
        if (optionalSliderImage.isPresent()) {
            HomePageSlider sliderImage = optionalSliderImage.get();
            return imageMapper.toResponseDTO(sliderImage);
        }
        return null;
    }
    @Override
    public List<HomePageSliderResponseDto> getAllSliderImages() {
        List<HomePageSlider> sliderImages = imageRepository.findAll();
        return sliderImages.stream()
                .map(imageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
