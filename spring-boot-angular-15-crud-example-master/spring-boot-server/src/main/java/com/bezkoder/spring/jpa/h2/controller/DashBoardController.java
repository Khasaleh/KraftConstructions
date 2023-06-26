package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.DashBoardStatistics;
import com.bezkoder.spring.jpa.h2.service.DashBoardService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {
    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_PHOTOGRAPHER + "')")
    public DashBoardStatistics getHomepageStatistics() {
        return dashBoardService.getHomepageStatistics();
    }
}