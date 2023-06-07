package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.CareersApplicationRequestDto;
import com.bezkoder.spring.jpa.h2.dto.CareersApplicationResponseDto;

import java.util.List;

public interface CareersApplicationService {
    CareersApplicationResponseDto saveApplication(CareersApplicationRequestDto requestDto);
    CareersApplicationResponseDto getApplicationById(Long id);
    List<CareersApplicationResponseDto> getAllApplications();
    void deleteApplicationById(Long id);
}
