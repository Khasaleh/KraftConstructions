package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDetailsRequestDTO {
    private Long serviceId;
    private MultipartFile beforeImage;
    private MultipartFile afterImage;
    private String beforeImageTitle;
    private String afterImageTitle;
    private String description;
    private boolean addPortfolio;
    private String author;
    private LocalDateTime updateDate;
}