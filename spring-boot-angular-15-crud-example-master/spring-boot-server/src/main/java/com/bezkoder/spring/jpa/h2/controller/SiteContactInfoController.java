package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.MessageResponse;
import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDto;
import com.bezkoder.spring.jpa.h2.service.SiteContactInfoService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/siteContactInfo")
public class SiteContactInfoController {
    @Autowired
    private SiteContactInfoService siteContactInfoService;

    @GetMapping
    public ResponseEntity<SiteContactInfoDto> getSiteContactInfo() {
        SiteContactInfoDto siteContactInfoDTO = siteContactInfoService.getSiteContactInfo();
        return ResponseEntity.ok(siteContactInfoDTO);
    }

    @PutMapping
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<?> updateSiteContactInfo(
                                                                     @Valid @RequestBody SiteContactInfoDto siteContactInfoDto) {
        SiteContactInfoDto updatedSiteContactInfoDto = siteContactInfoService.updateSiteContactInfo( siteContactInfoDto);
        return ResponseEntity.ok(new MessageResponse("Site ContactInfo Updated Successfully"));
    }
}