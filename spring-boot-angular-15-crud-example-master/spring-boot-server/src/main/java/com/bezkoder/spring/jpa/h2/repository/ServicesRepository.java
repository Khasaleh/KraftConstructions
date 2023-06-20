package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    Optional<Services> findByServiceName(String serviceName);
    List<Services> findAllByIsActive(boolean isActive);



    @Query("SELECT i FROM Services i WHERE i.pageName = ?1")
    Optional<Services> findServiceByPage(String pageName);
}

