package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.TestimonialSliderImage;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderRequestDto;
import com.bezkoder.spring.jpa.h2.dto.TestimonialSliderResponseDto;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.TestimonialSliderMapper;
import com.bezkoder.spring.jpa.h2.repository.TestimonialSliderImageRepository;
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
public class TestimonialSliderServiceImpl implements TestimonialSliderService{
    private static final String UPLOAD_DIRECTORY = "uploads/testimonialslider";
    private static final int MAX_IMAGES = 5;

    @Autowired
    private TestimonialSliderImageRepository imageRepository;
    @Autowired
    private TestimonialSliderMapper imageMapper;
    @Override
    public TestimonialSliderResponseDto saveOrUpdateSliderImage(TestimonialSliderRequestDto requestDTO) throws IOException {
        List<TestimonialSliderImage> testimonialSliderImageList = imageRepository.findAll();
        if(testimonialSliderImageList.size()<MAX_IMAGES) {
            TestimonialSliderImage entity = imageMapper.toEntity(requestDTO);
            MultipartFile imageFile = requestDTO.getImage();
            String imageUrl = saveImage(imageFile);

            entity.setImageUrl(imageUrl);
            TestimonialSliderImage savedEntity = imageRepository.save(entity);
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
        String relativePath = "/testimonialslider/" + uploadPath.relativize(filePath).toString();
        return relativePath;
    }
    @Override
    public TestimonialSliderResponseDto getSliderImageById(Long id) {
        Optional<TestimonialSliderImage> optionalSliderImage = imageRepository.findById(id);
        if (optionalSliderImage.isPresent()) {
            TestimonialSliderImage sliderImage = optionalSliderImage.get();
            return imageMapper.toResponseDTO(sliderImage);
        }
        return null;
    }
    @Override
    public List<TestimonialSliderResponseDto> getAllSliderImages() {
        List<TestimonialSliderImage> sliderImages = imageRepository.findAll();
        return sliderImages.stream()
                .map(imageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    public void deleteSliderImageById(Long id) {
        Optional<TestimonialSliderImage> optionalSliderImage = imageRepository.findById(id);
        if (optionalSliderImage.isPresent()) {
            TestimonialSliderImage sliderImage = optionalSliderImage.get();
            String imageUrl = sliderImage.getImageUrl();
            deleteImageFile(imageUrl);
            imageRepository.delete(sliderImage);
        }
    }
    @Override
    public TestimonialSliderResponseDto uploadMultipleImages(TestimonialSliderRequestDto requestDTO) throws IOException {
        List<TestimonialSliderImage> testimonialSliderImageList = imageRepository.findAll();

        int totalImages = testimonialSliderImageList.size() + requestDTO.getImages().size();
        if (totalImages <= MAX_IMAGES) {
            List<MultipartFile> imageFiles = requestDTO.getImages();
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                String s = saveImage(imageFile);
                imageUrls.add(s);
            }

            List<TestimonialSliderImage> savedEntities = imageUrls.stream()
                    .map(imageUrl -> {
                        TestimonialSliderImage entity = imageMapper.toEntity(requestDTO);
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
