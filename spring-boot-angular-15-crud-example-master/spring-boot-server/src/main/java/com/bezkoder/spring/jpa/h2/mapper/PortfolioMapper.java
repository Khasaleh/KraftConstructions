package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {

    public static Portfolio toEntity(PortfolioDTO portfolioDTO) {
        Portfolio entity = new Portfolio();
        entity.setImageUrl(portfolioDTO.getImageUrl());
        return entity;
    }
    public static   PortfolioDTO toDTO(Portfolio entity) {
        return new PortfolioDTO(entity.getId(), entity.getImageUrl());
    }


}
