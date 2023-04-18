package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
public class CareerNewsMapper {
    public static CareersNews mapToEntity(CareerNewsDto newsDTO) {
        CareersNews news = new CareersNews();
        news.setId(newsDTO.getId());
        news.setDescription(newsDTO.getDescription());
        news.setBackgroundColor(newsDTO.getBackgroundColor());
        news.setTextColor(newsDTO.getTextColor());
        news.setStartDate(newsDTO.getStartDate());
        news.setEndDate(newsDTO.getEndDate());
        news.setLabelEnabled(newsDTO.isLabelEnabled());
        return news;
    }
    public static CareerNewsDto mapToDTO(CareersNews news) {
        CareerNewsDto newsDTO = new CareerNewsDto();
        newsDTO.setId(news.getId());
        newsDTO.setDescription(news.getDescription());
        newsDTO.setBackgroundColor(news.getBackgroundColor());
        newsDTO.setTextColor(news.getTextColor());
        newsDTO.setStartDate(news.getStartDate());
        newsDTO.setEndDate(news.getEndDate());
        newsDTO.setLabelEnabled(news.isLabelEnabled());
        return newsDTO;
    }
}
