package com.bezkoder.spring.jpa.h2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceHomePageResponseDto {

    private Long id;
    private String description;
    private String afterImageUrl;
    private String serviceName;
}
