package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.AboutUs;
import com.bezkoder.spring.jpa.h2.dto.AboutUsRequestDTO;
import com.bezkoder.spring.jpa.h2.mapper.AboutUsMapper;
import com.bezkoder.spring.jpa.h2.repository.AboutUsRepository;
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
import java.util.Objects;
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
            AboutUs aboutUs = new AboutUs();
            aboutUs.setTitle(aboutUsRequestDto.getTitle());
            aboutUs.setDescription(aboutUsRequestDto.getDescription());
            return aboutUsRepository.save(aboutUs);

        }
    }
    @Override
    public void uploadAboutUsImage(Long id, MultipartFile image){
        Optional<AboutUs> aboutUsOptional = aboutUsRepository.findById(id);
        if(aboutUsOptional.isPresent()){
            AboutUs aboutUs = aboutUsOptional.get();
            String imageUrl = null;
            try {
                imageUrl = saveImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            aboutUs.setImageUrl(imageUrl);
            aboutUsRepository.save(aboutUs);
        }
        else {
            AboutUs aboutUs = new AboutUs();
            String imageUrl = null;
            try {
                imageUrl = saveImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            aboutUs.setImageUrl(imageUrl);
            aboutUsRepository.save(aboutUs);
        }
    }
    public String saveImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path uploadPath = Paths.get("uploads/aboutusimage");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = image.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        String absolutePath = filePath.toAbsolutePath().toString();
        return absolutePath;
    }
}
