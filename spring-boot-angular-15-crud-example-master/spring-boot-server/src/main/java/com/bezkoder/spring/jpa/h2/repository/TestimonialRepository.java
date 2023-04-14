package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.entity.Testimonial;
import com.bezkoder.spring.jpa.h2.entity.TestimonialPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

    List<Testimonial> findByPage(TestimonialPage page);

}