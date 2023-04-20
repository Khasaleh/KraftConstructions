package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;

public interface CareerNewsService {


    CareerNewsDto addNews(CareerNewsDto newsDTO);
    CareerNewsDto updateNews(Long id, CareerNewsDto newsDto);
    CareersNews findById(Long id);

    void save(CareersNews news);

    }

