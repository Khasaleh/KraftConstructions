package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ContactUsDto;

import javax.validation.Valid;
import java.util.List;

public interface ContactUsService {


    ContactUsDto createContactUs(@Valid ContactUsDto contactUsDTO);



    List<ContactUsDto> getAllContactUs();



    void deleteContactUs(Long id);
}