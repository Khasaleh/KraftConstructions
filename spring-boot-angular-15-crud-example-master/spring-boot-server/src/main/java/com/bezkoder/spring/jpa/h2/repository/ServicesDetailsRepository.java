package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.ServiceDetails;

import com.bezkoder.spring.jpa.h2.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesDetailsRepository extends JpaRepository<ServiceDetails, Long> {

    Optional<ServiceDetails> findByServices(Services services);


}