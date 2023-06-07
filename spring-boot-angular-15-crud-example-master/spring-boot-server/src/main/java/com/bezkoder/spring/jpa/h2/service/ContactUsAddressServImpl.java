package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.ContactUsAddress;
import com.bezkoder.spring.jpa.h2.dto.ContactUsAddressRequest;
import com.bezkoder.spring.jpa.h2.mapper.ContactUsAddressMapper;
import com.bezkoder.spring.jpa.h2.repository.ContactUsAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ContactUsAddressServImpl implements ContactUsAddressService {
    @Autowired
    private ContactUsAddressRepository contactUsAddressRepository;

    @Autowired
    private ContactUsAddressMapper contactUsAddressMapper;
    @Override
    public ContactUsAddress getAddress(Long id){
        Optional<ContactUsAddress> optionalContactUsAddress = contactUsAddressRepository.findById(id);
        return optionalContactUsAddress.orElse(null);
    }
    @Override
    public ContactUsAddress updateAddress(Long id, ContactUsAddressRequest contactUsAddressRequest) {
        Optional<ContactUsAddress> optionalContactUsAddress = contactUsAddressRepository.findById(id);
        if (optionalContactUsAddress.isPresent()) {
            ContactUsAddress contactUsAddress = optionalContactUsAddress.get();
            contactUsAddress.setAddress(contactUsAddressRequest.getAddress());
            return contactUsAddressRepository.save(contactUsAddress);
        } else {
            ContactUsAddress contactUsAddress = new ContactUsAddress();
            contactUsAddress.setAddress(contactUsAddressRequest.getAddress());
            return contactUsAddressRepository.save(contactUsAddress);

        }
    }
}
