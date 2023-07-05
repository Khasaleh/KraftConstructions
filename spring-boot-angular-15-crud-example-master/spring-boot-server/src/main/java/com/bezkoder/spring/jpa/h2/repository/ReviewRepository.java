package com.bezkoder.spring.jpa.h2.repository;


import com.bezkoder.spring.jpa.h2.Entity.ApprovalStatus;
import com.bezkoder.spring.jpa.h2.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByApprovalStatus(ApprovalStatus approvalStatus);

}
