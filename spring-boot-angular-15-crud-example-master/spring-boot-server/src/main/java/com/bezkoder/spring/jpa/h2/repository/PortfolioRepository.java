package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

}



