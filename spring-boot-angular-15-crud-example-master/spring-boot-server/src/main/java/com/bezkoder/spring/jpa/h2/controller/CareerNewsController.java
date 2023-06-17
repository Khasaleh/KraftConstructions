package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.CareersNews;
import com.bezkoder.spring.jpa.h2.dto.CareerNewsDto;
import com.bezkoder.spring.jpa.h2.dto.MessageResponse;
import com.bezkoder.spring.jpa.h2.mapper.CareerNewsMapper;
import com.bezkoder.spring.jpa.h2.service.CareerNewsService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/news")
public class CareerNewsController {

    @Autowired
    private CareerNewsService newsService;

    @Autowired
    private CareerNewsMapper newsMapper;
    private static final Long CAREER_NEWS = 1L;

    @GetMapping
    public ResponseEntity <CareersNews> getByid() {
        CareersNews careerNewsList = newsService.findById(CAREER_NEWS);
        return ResponseEntity.ok(careerNewsList);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<List<CareerNewsDto>> getCareerNewsByStatus(@PathVariable Boolean status) {
        List<CareerNewsDto> careerNewsList = newsService.findByStatus(status);
        List<CareerNewsDto> careerNewsDTOList = newsMapper.toDTOListStatus(careerNewsList);
        return ResponseEntity.ok().body(careerNewsDTOList);
    }

    // Create a new News
    @PostMapping
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<CareerNewsDto> addNews(@Valid  @RequestBody CareerNewsDto newsDTO) {
        CareerNewsDto addedNews = newsService.addNews(CAREER_NEWS,newsDTO);
        return ResponseEntity.ok(addedNews);
    }
    @PutMapping("/update-status")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<MessageResponse> updateLinkStatus() {
        boolean updatedLinkStatus = newsService.updateStatus(CAREER_NEWS);
        if (updatedLinkStatus) {
            return ResponseEntity.ok(new MessageResponse("Link status updated to true"));
        } else {
            return ResponseEntity.ok(new MessageResponse("Link status updated to false"));
        }
    }

}