package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.CareersApplication;
import com.bezkoder.spring.jpa.h2.dto.CareersApplicationRequestDto;
import com.bezkoder.spring.jpa.h2.dto.CareersApplicationResponseDto;
import com.bezkoder.spring.jpa.h2.mapper.CareerApplicationMapper;
import com.bezkoder.spring.jpa.h2.repository.CareersApplicationRepository;
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
public class CareersApplicationServiceImpl implements CareersApplicationService {
    @Autowired
    private CareersApplicationRepository applicationRepository;
    @Autowired
    private CareerApplicationMapper applicationMapper;
    @Override
    public CareersApplicationResponseDto getApplicationById(Long id) {
        Optional<CareersApplication> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            return applicationMapper.mapEntityToResponseDto(application.get());
        } else {
            // Handle application not found
            throw new RuntimeException("Application not found with id: " + id);
        }
    }

    @Override
    public List<CareersApplicationResponseDto> getAllApplications() {
        List<CareersApplication> applications = applicationRepository.findAll();
        return applications.stream()
                .map(applicationMapper::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public CareersApplicationResponseDto saveApplication(CareersApplicationRequestDto requestDto) {
        CareersApplication application = applicationMapper.mapRequestDtoToEntity(requestDto);

        // Save the resume file
        String resumeFileName = null;
        try {
            resumeFileName = saveResumeFile(requestDto.getResume());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        application.setResumeUrl(resumeFileName);

        CareersApplication savedApplication = applicationRepository.save(application);
        return applicationMapper.mapEntityToResponseDto(savedApplication);
    }
    @Override
    public void deleteApplicationById(Long id) {
        Optional<CareersApplication> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            CareersApplication careersApplication = application.get();
            applicationRepository.delete(careersApplication);
        } else {
            // Handle application not found
            throw new RuntimeException("Application not found with id: " + id);
        }
    }

    private String saveResumeFile(MultipartFile resume) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(resume.getOriginalFilename()));
        String uploadDir = "uploads/resumes";
        Path uploadPath = Paths.get("src/main/resources/static", uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = resume.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}
