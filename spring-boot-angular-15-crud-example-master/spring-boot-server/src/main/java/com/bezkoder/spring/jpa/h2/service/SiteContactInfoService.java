package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDto;

public interface SiteContactInfoService {
    SiteContactInfoDto getSiteContactInfo();
    SiteContactInfoDto updateSiteContactInfo(SiteContactInfoDto siteContactInfoDto);
}
