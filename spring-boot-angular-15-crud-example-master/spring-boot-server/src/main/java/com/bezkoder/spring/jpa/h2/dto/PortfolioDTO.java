package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PortfolioDTO {
    private Long id;
    private String imageUrl;
    private String heading;
    private String description;
    private String linkUrl;

    public static PortfolioDTO fromEntity(Portfolio entity) {
        PortfolioDTO dto = new PortfolioDTO();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setHeading(entity.getHeading());
        dto.setDescription(entity.getDescription());
        dto.setLinkUrl(entity.getLinkUrl());
        return dto;
    }

    public Portfolio toEntity() {
        Portfolio entity = new Portfolio();
        entity.setImageUrl(this.imageUrl);
        entity.setHeading(this.heading);
        entity.setDescription(this.description);
        entity.setLinkUrl(this.linkUrl);
        return entity;
    }
}
