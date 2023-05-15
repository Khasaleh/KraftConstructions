package com.bezkoder.spring.jpa.h2.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceHomePageRequestDto {
    List<Long> serviceIds;
}
