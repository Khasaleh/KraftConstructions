package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CareerNewsRepository extends JpaRepository<CareersNews, Long> {

    List<CareersNews> findByStatus(Boolean status);

//    Optional<CareersNews> findByStatus(Boolean status);


}