package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;

import com.bezkoder.spring.jpa.h2.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesDetailsRepository extends JpaRepository<ServiceDetails, Long> {

    List<ServiceDetails> findByServices(Services services);

}