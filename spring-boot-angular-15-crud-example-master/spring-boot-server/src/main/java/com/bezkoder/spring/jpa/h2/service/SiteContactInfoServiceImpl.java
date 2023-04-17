package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.SiteContactInfo;
import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDTO;
import com.bezkoder.spring.jpa.h2.mapper.SiteContactInfoMapper;
import com.bezkoder.spring.jpa.h2.repository.SiteContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SiteContactInfoServiceImpl implements SiteContactInfoService {
    @Autowired
    private SiteContactInfoRepository siteContactInfoRepository;
    @Autowired
    private SiteContactInfoMapper siteContactInfoMapper;

    @Override
    public SiteContactInfoDTO getSiteContactInfo() {
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
            throw new EntityNotFoundException("SiteContactInfo not found with id: " + contactInfoId);
        }
    }
}