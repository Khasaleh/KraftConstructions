package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ServiceDetailsDTO {
    private Long id;
    private String serviceName;
    private String description;
    private String beforeImageUrl;
    private String afterImageUrl;
    private Boolean addPortfolio;
    private LocalDateTime startDate;
}
