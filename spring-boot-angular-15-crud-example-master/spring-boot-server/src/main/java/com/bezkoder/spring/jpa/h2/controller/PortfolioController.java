package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import com.bezkoder.spring.jpa.h2.mapper.PortfolioMapper;
import com.bezkoder.spring.jpa.h2.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private PortfolioMapper portfolioMapper;

    @GetMapping
    public List<PortfolioDTO> findAll() {
        return portfolioService.findAll();
    }

    @GetMapping("/{id}")
    public PortfolioDTO findById(@PathVariable Long id) {
        return portfolioService.findById(id);
    }

    @PostMapping("/addportfolio")
    public ResponseEntity<PortfolioDTO> create(@RequestBody PortfolioDTO portfolioDTO) {
        PortfolioDTO createdPortfolioDTO = portfolioService.save(portfolioDTO);
        return new ResponseEntity<>(createdPortfolioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PortfolioDTO> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioDTO portfolioDto) {
        Portfolio updatedPortfolio = portfolioService.updatePortfolio(id, portfolioDto);
        PortfolioDTO updatedPortfolioDTO = portfolioMapper.toDTO(updatedPortfolio);
        return ResponseEntity.ok(updatedPortfolioDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        portfolioService.delete(id);
        return ResponseEntity.ok().build();
    }
}