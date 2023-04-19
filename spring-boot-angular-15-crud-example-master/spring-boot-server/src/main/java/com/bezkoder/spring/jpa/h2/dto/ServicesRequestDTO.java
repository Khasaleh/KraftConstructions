package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServicesRequestDTO {
    private Long id;
    private String serviceName;
    private String pageName;
    private boolean isActive;



}
