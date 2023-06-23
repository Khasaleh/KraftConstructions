package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.DashBoardStatistics;

public interface DashBoardService {
    DashBoardStatistics getHomepageStatistics();
}
