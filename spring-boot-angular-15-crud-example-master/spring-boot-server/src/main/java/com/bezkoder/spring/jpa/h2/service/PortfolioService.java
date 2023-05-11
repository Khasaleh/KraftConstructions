package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PortfolioService {
    PortfolioDTO findById(Long id);

    PortfolioDTO save(PortfolioDTO portfolioDTO);

    void delete(Long id);
}

