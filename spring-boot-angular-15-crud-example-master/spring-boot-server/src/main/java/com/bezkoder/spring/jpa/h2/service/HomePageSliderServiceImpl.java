package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.HomePageSlider;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.HomePageSliderResponseDto;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.HomePageSliderMapper;
import com.bezkoder.spring.jpa.h2.repository.HomePageSliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomePageSliderServiceImpl implements HomePageSliderService{
    private static final String UPLOAD_DIRECTORY = "uploads/homepagebanner";
    private static final int MAX_IMAGES = 5;

    @Autowired
    private HomePageSliderRepository imageRepository;
    @Autowired
    private HomePageSliderMapper imageMapper;
    @Override
    public HomePageSliderResponseDto saveOrUpdateSliderImage(HomePageSliderRequestDto requestDTO) throws IOException {
        List<HomePageSlider> homePageSliderList = imageRepository.findAll();
        if(homePageSliderList.size()<MAX_IMAGES) {
            HomePageSlider entity = imageMapper.toEntity(requestDTO);
            MultipartFile imageFile = requestDTO.getImage();
            String imageUrl = saveImage(imageFile);

            entity.setImageUrl(imageUrl);
            HomePageSlider savedEntity = imageRepository.save(entity);
            return imageMapper.toResponseDTO(savedEntity);
        }
        else
            throw new GenericException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE,"Limit Reached"," Maximum images uploaded");
    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
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
    public void deleteSliderImageById(Long id) {
        Optional<HomePageSlider> optionalSliderImage = imageRepository.findById(id);
        if (optionalSliderImage.isPresent()) {
            HomePageSlider sliderImage = optionalSliderImage.get();
            String imageUrl = sliderImage.getImageUrl();
            deleteImageFile(imageUrl);
            imageRepository.delete(sliderImage);
        }
    }
    @Override
    public HomePageSliderResponseDto uploadMultipleImages(HomePageSliderRequestDto requestDTO) throws IOException {
        List<HomePageSlider> homePageSliderList = imageRepository.findAll();

        int totalImages = homePageSliderList.size() + requestDTO.getImages().size();
        if (totalImages <= MAX_IMAGES) {
            List<MultipartFile> imageFiles = requestDTO.getImages();
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                String s = saveImage(imageFile);
                imageUrls.add(s);
            }

            List<HomePageSlider> savedEntities = imageUrls.stream()
                    .map(imageUrl -> {
                        HomePageSlider entity = imageMapper.toEntity(requestDTO);
                        entity.setImageUrl(imageUrl);
                        return imageRepository.save(entity);
                    })
                    .collect(Collectors.toList());

            return savedEntities.stream()
                    .map(imageMapper::toResponseDTO)
                    .findFirst()
                    .orElse(null);
        } else {
            throw new GenericException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "Limit Exceeded", "Maximum images limit exceeded");
        }
    }


    private void deleteImageFile(String imageUrl) {
        if (imageUrl != null) {
            String filePath = UPLOAD_DIRECTORY + imageUrl.substring(imageUrl.lastIndexOf('/'));
            Path deletePath = Paths.get(filePath);
            try {
                Files.deleteIfExists(deletePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
