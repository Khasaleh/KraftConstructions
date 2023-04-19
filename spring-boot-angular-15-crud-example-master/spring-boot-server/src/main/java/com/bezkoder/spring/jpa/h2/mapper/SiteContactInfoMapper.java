package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.SiteContactInfo;
import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDto;
import org.springframework.stereotype.Component;

@Component
public class SiteContactInfoMapper {
    public SiteContactInfoDto toDTO(SiteContactInfo entity) {
        SiteContactInfoDto dto = new SiteContactInfoDto();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setCompany_Phones(entity.getCompany_Phones());
        dto.setFax(entity.getFax());
        return dto;
    }

    public SiteContactInfo toEntity(SiteContactInfoDto dto) {
        SiteContactInfo entity = new SiteContactInfo();
        entity.setAddress(dto.getAddress());
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setCompany_Phones(dto.getCompany_Phones());
        entity.setFax(dto.getFax());
        return entity;
    }
}