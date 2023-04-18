package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.Entity.ERole;
import com.bezkoder.spring.jpa.h2.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
