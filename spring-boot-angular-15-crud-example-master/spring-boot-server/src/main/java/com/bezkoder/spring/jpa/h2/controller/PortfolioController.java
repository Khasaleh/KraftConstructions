package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import com.bezkoder.spring.jpa.h2.mapper.PortfolioMapper;
import com.bezkoder.spring.jpa.h2.service.PortfolioService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private PortfolioMapper portfolioMapper;

    @GetMapping("/{id}")
    public PortfolioDTO findById(@PathVariable Long id) {
        return portfolioService.findById(id);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        portfolioService.delete(id);
        return ResponseEntity.ok().build();
    }
}