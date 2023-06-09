package com.bezkoder.spring.jpa.h2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannerResponseDTO {
    private String bannerLink;
    private String bannerDescription;
    private boolean linkStatus;
}