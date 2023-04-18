package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerNewsRepository extends JpaRepository<CareersNews, Long> {
}