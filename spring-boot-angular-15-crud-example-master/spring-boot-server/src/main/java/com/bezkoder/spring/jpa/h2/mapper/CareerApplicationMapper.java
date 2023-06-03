package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.CareersApplication;
import com.bezkoder.spring.jpa.h2.dto.CareersApplicationRequestDto;
import com.bezkoder.spring.jpa.h2.dto.CareersApplicationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CareerApplicationMapper {
    public CareersApplication mapRequestDtoToEntity(CareersApplicationRequestDto requestDto) {
        CareersApplication careerApplication = new CareersApplication();
        careerApplication.setFirstName(requestDto.getFirstName());
        careerApplication.setLastName(requestDto.getLastName());
        careerApplication.setEmail(requestDto.getEmail());
        careerApplication.setPhoneNumber(requestDto.getPhoneNumber());
        careerApplication.setAddress(requestDto.getAddress());
        careerApplication.setCity(requestDto.getCity());
        careerApplication.setState(requestDto.getState());
        careerApplication.setZip(requestDto.getZip());
        careerApplication.setWorkExperience(requestDto.getWorkExperience());
        careerApplication.setJobType(requestDto.getJobType());
        careerApplication.setWorkRestrictions(requestDto.getWorkRestrictions());
        careerApplication.setHoursRestrictions(requestDto.getHoursRestrictions());
        careerApplication.setReferences(requestDto.getReferences());
        careerApplication.setOtherNotes(requestDto.getOtherNotes());
        return careerApplication;
    }
    public CareersApplicationResponseDto mapEntityToResponseDto(CareersApplication careerApplication) {
        CareersApplicationResponseDto responseDto = new CareersApplicationResponseDto();
        responseDto.setId(careerApplication.getId());
        responseDto.setFirstName(careerApplication.getFirstName());
        responseDto.setLastName(careerApplication.getLastName());
        responseDto.setEmail(careerApplication.getEmail());
        responseDto.setPhoneNumber(careerApplication.getPhoneNumber());
        responseDto.setAddress(careerApplication.getAddress());
        responseDto.setCity(careerApplication.getCity());
        responseDto.setState(careerApplication.getState());
        responseDto.setZip(careerApplication.getZip());
        responseDto.setWorkExperience(careerApplication.getWorkExperience());
        responseDto.setJobType(careerApplication.getJobType());
        responseDto.setWorkRestrictions(careerApplication.getWorkRestrictions());
        responseDto.setHoursRestrictions(careerApplication.getHoursRestrictions());
        responseDto.setReferences(careerApplication.getReferences());
        responseDto.setOtherNotes(careerApplication.getOtherNotes());
        responseDto.setResumeUrl(careerApplication.getResumeUrl());
        return responseDto;
    }
}
