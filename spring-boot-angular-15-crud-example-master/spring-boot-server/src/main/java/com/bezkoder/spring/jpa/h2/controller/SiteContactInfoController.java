package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDto;
import com.bezkoder.spring.jpa.h2.service.SiteContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/siteContactInfo")
public class SiteContactInfoController {
    @Autowired
    private SiteContactInfoService siteContactInfoService;

    @GetMapping
    public ResponseEntity<SiteContactInfoDto> getSiteContactInfo() {
        SiteContactInfoDto siteContactInfoDTO = siteContactInfoService.getSiteContactInfo();
        return ResponseEntity.ok(siteContactInfoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteContactInfoDto> updateSiteContactInfo( @PathVariable("id") Long id,
                                                                     @Valid @RequestBody SiteContactInfoDto siteContactInfoDto) {
        SiteContactInfoDto updatedSiteContactInfoDto = siteContactInfoService.updateSiteContactInfo(id, siteContactInfoDto);
        return ResponseEntity.ok(updatedSiteContactInfoDto);
    }
}