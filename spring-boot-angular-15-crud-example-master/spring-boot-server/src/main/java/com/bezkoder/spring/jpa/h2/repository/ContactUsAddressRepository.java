package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.ContactUsAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsAddressRepository extends JpaRepository<ContactUsAddress,Long> {
}
