package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import com.bezkoder.spring.jpa.h2.mapper.CareerNewsMapper;
import com.bezkoder.spring.jpa.h2.service.CareerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class CareerNewsController {

    @Autowired
    private CareerNewsService newsService;

    @Autowired
    private CareerNewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<List<CareerNewsDto>> findAll() {
        List<CareerNewsDto> careerNewsList = newsService.findAll();
        return ResponseEntity.ok(careerNewsList);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CareerNewsDto>> getCareerNewsByStatus(@PathVariable Boolean status) {
        List<CareerNewsDto> careerNewsList = newsService.findByStatus(status);
        List<CareerNewsDto> careerNewsDTOList = newsMapper.toDTOListStatus(careerNewsList);
        return ResponseEntity.ok().body(careerNewsDTOList);
    }




    // Create a new News
    @PostMapping
    public ResponseEntity<CareerNewsDto> addNews(@Valid  @RequestBody CareerNewsDto newsDTO) {
        CareerNewsDto addedNews = newsService.addNews(newsDTO);
        return ResponseEntity.ok(addedNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareerNewsDto> updateNews(@PathVariable Long id, @Valid @RequestBody CareerNewsDto newsDto) {
        CareerNewsDto updateService =  newsService.updateNews(id, newsDto);
        return ResponseEntity.ok(updateService);

    }



}