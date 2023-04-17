package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDTO;
import com.bezkoder.spring.jpa.h2.service.SiteContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/siteContactInfo")
public class SiteContactInfoController {
    @Autowired
    private SiteContactInfoService siteContactInfoService;

    @GetMapping
    public ResponseEntity<SiteContactInfoDTO> getSiteContactInfo() {
        SiteContactInfoDTO siteContactInfoDTO = siteContactInfoService.getSiteContactInfo();
        return ResponseEntity.ok(siteContactInfoDTO);
    }
}