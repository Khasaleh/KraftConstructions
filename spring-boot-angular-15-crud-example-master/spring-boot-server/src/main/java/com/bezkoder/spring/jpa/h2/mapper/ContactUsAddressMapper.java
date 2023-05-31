package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.ContactUsAddress;
import com.bezkoder.spring.jpa.h2.dto.ContactUsAddressResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactUsAddressMapper {
    public ContactUsAddressResponse toDto(ContactUsAddress entity){
        ContactUsAddressResponse dto = new ContactUsAddressResponse();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}
