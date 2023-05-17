package com.bezkoder.spring.jpa.h2.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PortfolioServiceRequestDTO {
    private Long portfolioColumn;
    List<Long> portfoliosId;
}
