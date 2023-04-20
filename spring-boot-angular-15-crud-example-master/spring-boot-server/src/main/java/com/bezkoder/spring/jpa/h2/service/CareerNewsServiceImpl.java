package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import com.bezkoder.spring.jpa.h2.mapper.CareerNewsMapper;
import com.bezkoder.spring.jpa.h2.repository.CareerNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CareerNewsServiceImpl implements CareerNewsService {
    @Autowired
    private CareerNewsRepository newsRepository;

    @Autowired
    private CareerNewsMapper newsMapper;


    @Override
    public CareersNews findById(Long id) {
        Optional<CareersNews> optionalNews = newsRepository.findById(id);
        return optionalNews.orElse(null);
    }

    @Override
    public void save(CareersNews news) {
        newsRepository.save(news);
    }


    @Override
    public CareerNewsDto addNews(CareerNewsDto newsDTO) {
        CareersNews news = CareerNewsMapper.mapToEntity(newsDTO);
        news = newsRepository.save(news);
        return CareerNewsMapper.mapToDTO(news);
    }

    @Override
    public CareerNewsDto updateNews(Long id, CareerNewsDto newsDto) {
        CareersNews news = findById(id);
        if (news == null) {
            throw new RuntimeException("News not found for id: " + id);
        }
        CareersNews updatedNews = newsMapper.mapToEntity(newsDto);
        updatedNews.setId(news.getId());
        save(updatedNews);
        return CareerNewsMapper.mapToDTO(updatedNews);
    }

}