package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDetailsResponseDTO {
    private Long id;
    private Long serviceId;
    private String beforeImageUrl;
    private String afterImageUrl;
    private String beforeImageTitle;
    private String afterImageTitle;
    private String description;
    private boolean addPortfolio;
    private String author;
    private LocalDateTime updateDate;
}
