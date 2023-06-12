package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.ContactUsAddress;
import com.bezkoder.spring.jpa.h2.dto.ContactUsAddressRequest;

public interface ContactUsAddressService {
    ContactUsAddress getAddress(Long id);
    ContactUsAddress updateAddress(Long id, ContactUsAddressRequest contactUsAddressRequest);
}
