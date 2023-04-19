package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import com.bezkoder.spring.jpa.h2.service.CareerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class CareerNewsController {

    @Autowired
    private CareerNewsService newsService;

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