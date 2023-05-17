package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.Portfolio;
import com.bezkoder.spring.jpa.h2.dto.PortfolioDTO;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.PortfolioMapper;
import com.bezkoder.spring.jpa.h2.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;



    @Override
    public PortfolioDTO findById(Long id) {
        Portfolio portfolioItem = portfolioRepository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND," Portfolio item not found for id: " +id,"Incorrect id"));
        return PortfolioMapper.toDTO(portfolioItem);
    }

    @Override
    public PortfolioDTO save(PortfolioDTO portfolioItemDTO) {
        Portfolio portfolio = PortfolioMapper.toEntity(portfolioItemDTO);
        portfolio = portfolioRepository.save(portfolio);
        return PortfolioMapper.toDTO(portfolio);
    }


    @Override
    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }
}

