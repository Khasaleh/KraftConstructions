package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDetailsDTO {

    private Long id;
    private Long services_id;
    private String beforeImageUrl;
    private String afterImageUrl;
    private String description;
    private boolean addPortfolio;
    private String author;
    private LocalDateTime updateDate;



}
