package com.bezkoder.spring.jpa.h2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AboutUsResponseDTO {
    private Long id;
    private String description;
    private String imageUrl;

}