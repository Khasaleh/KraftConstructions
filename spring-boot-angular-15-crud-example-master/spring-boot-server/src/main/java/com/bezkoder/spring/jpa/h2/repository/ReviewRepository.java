package com.bezkoder.spring.jpa.h2.repository;


import com.bezkoder.spring.jpa.h2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {



}
