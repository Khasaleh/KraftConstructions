package com.bezkoder.spring.jpa.h2.service;


import com.bezkoder.spring.jpa.h2.Entity.ContactUs;
import com.bezkoder.spring.jpa.h2.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsService {

    @Autowired
    private  ContactUsRepository contactUsRepository;


    public List<ContactUs> getAllContactUsForms() {
        return contactUsRepository.findAll();
    }

    public ContactUs submitContactUsForm(ContactUs contactUs) {

        return contactUsRepository.save(contactUs);
    }



}
