package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import com.bezkoder.spring.jpa.h2.mapper.PortfolioMapper;
import com.bezkoder.spring.jpa.h2.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public List<PortfolioDTO> findAll() {
        List<Portfolio> portfolioItems = portfolioRepository.findAll();
        return PortfolioMapper.toDTOList(portfolioItems);
    }

    @Override
    public PortfolioDTO findById(Long id) {
        Portfolio portfolioItem = portfolioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Portfolio item with id " + id + " not found"));
        return PortfolioMapper.toDTO(portfolioItem);
    }

    @Override
    public PortfolioDTO save(PortfolioDTO portfolioItemDTO) {
        Portfolio portfolio = PortfolioMapper.toEntity(portfolioItemDTO);
        portfolio = portfolioRepository.save(portfolio);
        return PortfolioMapper.toDTO(portfolio);
    }

    @Override
    public Portfolio updatePortfolio(Long id, PortfolioDTO portfolioDto) {
        Portfolio existingPortfolio = portfolioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Portfolio item not found with id: " + id));
        existingPortfolio.setImageUrl(portfolioDto.getImageUrl());
        existingPortfolio.setHeading(portfolioDto.getHeading());
        existingPortfolio.setDescription(portfolioDto.getDescription());
        existingPortfolio.setLinkUrl(portfolioDto.getLinkUrl());
        return portfolioRepository.save(existingPortfolio);
    }

    @Override
    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }
}

