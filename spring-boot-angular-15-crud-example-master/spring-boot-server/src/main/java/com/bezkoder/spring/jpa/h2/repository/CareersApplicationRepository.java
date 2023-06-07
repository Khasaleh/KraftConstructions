package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.CareersApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareersApplicationRepository extends JpaRepository<CareersApplication,Long> {
}
