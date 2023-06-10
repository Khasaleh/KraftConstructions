package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.HomePageSlider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomePageSliderRepository extends JpaRepository<HomePageSlider,Long> {
}
