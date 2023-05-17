package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.ContactUs;
import com.bezkoder.spring.jpa.h2.dto.ContactUsDto;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.ContactUsMapper;
import com.bezkoder.spring.jpa.h2.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Autowired
    private ContactUsMapper contactUsMapper;

    @Override
    public List<ContactUsDto> getAllContactUs() {
        List<ContactUs> contactUsList = contactUsRepository.findAll();
        return contactUsMapper.toDtoList(contactUsList);
    }


    @Override
    public ContactUsDto createContactUs(@Valid ContactUsDto contactUsDto) {
        ContactUs contactUs = contactUsMapper.toEntity(contactUsDto);
        ContactUs createdContactUs = contactUsRepository.save(contactUs);
        return contactUsMapper.toDto(createdContactUs);
    }



    @Override
    public void deleteContactUs(Long id) {
        Optional<ContactUs> existingContactUsOptional = contactUsRepository.findById(id);
        if (existingContactUsOptional.isPresent()) {
            contactUsRepository.deleteById(id);
        } else {
//            throw new IllegalArgumentException("ContactUs with ID " + id + " not found");
            throw new GenericException(HttpStatus.NOT_FOUND,"ContactUs not found for id: " + id,"Incorrect id");
        }
    }
}