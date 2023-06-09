package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.mapper.CareerNewsMapper;
import com.bezkoder.spring.jpa.h2.repository.CareerNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public CareerNewsDto addNews(Long id, CareerNewsDto newsDTO) {
        CareersNews news;
        Optional<CareersNews> optionalNews = newsRepository.findById(id);

        if (optionalNews.isPresent()) {
            news = optionalNews.get();
            news.setDescription(newsDTO.getDescription());
            news.setBackgroundColor(newsDTO.getBackgroundColor());
            news.setTextColor(newsDTO.getTextColor());
            news.setStartDate(newsDTO.getStartDate());
            news.setEndDate(newsDTO.getEndDate());
            news.setStatus(newsDTO.isStatus());
        } else {
            news = CareerNewsMapper.mapToEntity(newsDTO);
        }

        news = newsRepository.save(news);
        return CareerNewsMapper.mapToDTO(news);
    }

    @Override
    public CareerNewsDto updateNews(Long id, CareerNewsDto newsDto) {
        CareersNews news = findById(id);
        if (news == null) {
            throw new GenericException(HttpStatus.NOT_FOUND, "News not found for id: " + id, "Incorrect id");
        }
        CareersNews updatedNews = newsMapper.mapToEntity(newsDto);
        updatedNews.setId(news.getId());
        save(updatedNews);
        return CareerNewsMapper.mapToDTO(updatedNews);
    }

    @Override
    public List<CareerNewsDto> findAll() {
        List<CareersNews> careerNewsList = newsRepository.findAll();
        return CareerNewsMapper.toDTOList(careerNewsList);
    }

    @Override
    public List<CareerNewsDto> findByStatus(Boolean status) {
        List<CareersNews> careerNewsList = newsRepository.findByStatus(status);
        return newsMapper.toDTOList(careerNewsList);
    }
    @Override
    public Boolean updateStatus(Long id) {
        CareersNews news = findById(id);
        if (news == null) {
            throw new GenericException(HttpStatus.NOT_FOUND, "News not found for id: " + id, "Incorrect id");
        }
        boolean updatedStatus = !news.isStatus();
        news.setStatus(updatedStatus);
        newsRepository.save(news);
        return updatedStatus;
    }

}