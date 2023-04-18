package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import com.bezkoder.spring.jpa.h2.mapper.CareerNewsMapper;
import com.bezkoder.spring.jpa.h2.repository.CareerNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerNewsServiceImpl implements CareerNewsService {
    @Autowired
    private CareerNewsRepository newsRepository;

    @Autowired
    private CareerNewsMapper newsMapper;





    @Override
    public CareerNewsDto addNews(CareerNewsDto newsDTO) {
        CareersNews news = CareerNewsMapper.mapToEntity(newsDTO);
        news = newsRepository.save(news);
        return CareerNewsMapper.mapToDTO(news);
    }

}