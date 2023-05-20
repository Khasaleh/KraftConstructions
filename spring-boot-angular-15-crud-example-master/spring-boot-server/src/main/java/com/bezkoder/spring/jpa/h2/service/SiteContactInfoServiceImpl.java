package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.SiteContactInfo;
import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDto;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.SiteContactInfoMapper;
import com.bezkoder.spring.jpa.h2.repository.SiteContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteContactInfoServiceImpl implements SiteContactInfoService {
    @Autowired
    private SiteContactInfoRepository siteContactInfoRepository;
    @Autowired
    private SiteContactInfoMapper siteContactInfoMapper;

    @Override
    public SiteContactInfoDto getSiteContactInfo() {
        // Implement custom logic to fetch the SiteContactInfo entity
        // For example, you can use JpaRepository methods like findById()
        // or custom JPQL queries to retrieve the entity from the repository

        // Example using JpaRepository method findById()
        Long contactInfoId = 1L; // Id of the contact info entity to fetch
        Optional<SiteContactInfo> contactInfoOptional = siteContactInfoRepository.findById(contactInfoId);

        // Check if the entity exists
        if (contactInfoOptional.isPresent()) {
            return contactInfoOptional.get();
        } else {
            // Throw an exception or handle the case when the entity is not found

            throw new GenericException(HttpStatus.NOT_FOUND,"SiteContactInfo not found with id: " + contactInfoId,"Incorrect id");
        }
    }
//GenericException(HttpStatus.NOT_FOUND," Service not found for id: " +id,"Incorrect id");
    @Override
    public SiteContactInfoDto updateSiteContactInfo(SiteContactInfoDto siteContactInfoDto) {
        // Retrieve the existing entity from the repository
        Optional<SiteContactInfo> siteContactInfoOptional = siteContactInfoRepository.findById(siteContactInfoDto.getId());

        // Check if the entity exists
        if (siteContactInfoOptional.isPresent()) {
            SiteContactInfo existingSiteContactInfo = siteContactInfoOptional.get();

            // Update the existing entity with the values from the DTO
            existingSiteContactInfo.setId(siteContactInfoDto.getId());
            existingSiteContactInfo.setAddress(siteContactInfoDto.getAddress());
            existingSiteContactInfo.setEmail(siteContactInfoDto.getEmail());
            existingSiteContactInfo.setCompany_Phones(siteContactInfoDto.getCompany_Phones());
            existingSiteContactInfo.setFax(siteContactInfoDto.getFax());
            // Update other fields as needed

            // Save the updated entity in the repository
            SiteContactInfo updatedSiteContactInfo = siteContactInfoRepository.save(existingSiteContactInfo);

            // Map the updated entity back to DTO
            SiteContactInfoDto updatedSiteContactInfoDto = siteContactInfoMapper.toDTO(updatedSiteContactInfo);

            return updatedSiteContactInfoDto;
        } else {
            // Throw an exception or handle the case when the entity is not found
            SiteContactInfo newContactUsInfo = siteContactInfoMapper.toEntity(siteContactInfoDto);
            return siteContactInfoRepository.save(newContactUsInfo);
        }
    }



}