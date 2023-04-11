package com.bezkoder.spring.jpa.h2.query;

import com.bezkoder.spring.jpa.h2.Entity.ContactUs;
import com.bezkoder.spring.jpa.h2.repository.ContactUsRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFormQuey  implements GraphQLQueryResolver {

    @Autowired
    public ContactUsRepository contactUsRepository;



    public List<ContactUs> getContactForm(){

        return contactUsRepository.findAll();

    }
}
