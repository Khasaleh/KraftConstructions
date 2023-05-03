package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortfolioMapper {
    public static PortfolioDTO toDTO(Portfolio entity) {
        return new PortfolioDTO(entity.getId(), entity.getImageUrl(), entity.getHeading(), entity.getDescription(), entity.getLinkUrl());
    }

    public static List<PortfolioDTO> toDTOList(List<Portfolio> entityList) {
        return entityList.stream().map(PortfolioMapper::toDTO).collect(Collectors.toList());
    }

    public static Portfolio toEntity(PortfolioDTO dto) {
        return dto.toEntity();
    }

    public static List<Portfolio> toEntityList(List<PortfolioDTO> dtoList) {
        return dtoList.stream().map(PortfolioDTO::toEntity).collect(Collectors.toList());
    }

}
