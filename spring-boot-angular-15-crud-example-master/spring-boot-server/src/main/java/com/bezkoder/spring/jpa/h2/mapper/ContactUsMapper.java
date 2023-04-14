package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.ContactUs;
import com.bezkoder.spring.jpa.h2.dto.ContactUsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactUsMapper {
    public ContactUsDto toDto(ContactUs contactUs) {
        ContactUsDto contactUsDto = new ContactUsDto();
        contactUsDto.setId(contactUs.getId());
        contactUsDto.setFirstname(contactUs.getFirstname());
        contactUsDto.setLastname(contactUs.getLastname());
        contactUsDto.setEmail(contactUs.getEmail());
        contactUsDto.setMessage(contactUs.getMessage());
        return contactUsDto;
    }

    public ContactUs toEntity(ContactUsDto contactUsDto) {
        ContactUs contactUs = new ContactUs();
        contactUs.setId(contactUsDto.getId());
        contactUs.setFirstname(contactUsDto.getFirstname());
        contactUs.setLastname(contactUsDto.getLastname());
        contactUs.setEmail(contactUsDto.getEmail());
        contactUs.setMessage(contactUsDto.getMessage());
        return contactUs;
    }

    public List<ContactUsDto> toDtoList(List<ContactUs> contactUsList) {
        return contactUsList.stream().map(this::toDto).collect(Collectors.toList());
    }
}