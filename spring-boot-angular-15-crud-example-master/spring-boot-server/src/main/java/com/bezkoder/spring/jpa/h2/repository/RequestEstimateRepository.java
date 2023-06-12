package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.EstimateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestEstimateRepository extends JpaRepository<EstimateRequest,Long> {
}
