package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.SiteContactInfo;
import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class SiteContactInfoMapper {
    public SiteContactInfoDTO toDTO(SiteContactInfo entity) {
        SiteContactInfoDTO dto = new SiteContactInfoDTO();
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setCompany_Phones(entity.getCompany_Phones());
        dto.setFax(entity.getFax());
        return dto;
    }

    public SiteContactInfo toEntity(SiteContactInfoDTO dto) {
        SiteContactInfo entity = new SiteContactInfo();
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setCompany_Phones(dto.getCompany_Phones());
        entity.setFax(dto.getFax());
        return entity;
    }
}