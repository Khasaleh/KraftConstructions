package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<Services, String> {
    Optional<Services> findByServiceName(String serviceName);

}

