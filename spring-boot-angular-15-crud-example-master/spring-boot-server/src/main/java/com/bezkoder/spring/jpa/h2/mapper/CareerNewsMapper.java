package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        news.setStatus(newsDTO.isStatus());
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
        newsDTO.setStatus(news.isStatus());
        return newsDTO;
    }

    public static List<CareerNewsDto> toDTOList(List<CareersNews> careerNewsList) {
        return careerNewsList.stream().map(CareerNewsMapper::mapToDTO).collect(Collectors.toList());
    }

    public List<CareerNewsDto> toDTOListStatus(List<CareerNewsDto> careerNewsList) {
        List<CareerNewsDto> careerNewsDTOList = new ArrayList<>();

        for (CareerNewsDto careerNews : careerNewsList) {
            CareerNewsDto careerNewsDTO = new CareerNewsDto();
            careerNewsDTO.setId(careerNews.getId());
            careerNewsDTO.setDescription(careerNews.getDescription());
            careerNewsDTO.setStatus(careerNews.isStatus());
            careerNewsDTO.setBackgroundColor(careerNews.getBackgroundColor());
            careerNewsDTO.setTextColor(careerNews.getTextColor());
            careerNewsDTO.setStartDate(careerNews.getStartDate());
            careerNewsDTO.setEndDate(careerNews.getEndDate());
            careerNewsDTOList.add(careerNewsDTO);
        }

        return careerNewsDTOList;
    }




}
