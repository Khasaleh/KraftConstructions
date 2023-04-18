package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.Entity.Slider;
import com.bezkoder.spring.jpa.h2.mapper.SliderMapper;
import com.bezkoder.spring.jpa.h2.repository.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SliderServiceImpl implements SliderService {

    private final SliderRepository sliderRepository;
    private final SliderMapper sliderMapper;

    @Autowired
    public SliderServiceImpl(SliderRepository sliderRepository, SliderMapper sliderMapper) {
        this.sliderRepository = sliderRepository;
        this.sliderMapper = sliderMapper;
    }


    @Override
    public SliderDto addSlider(Slider slider) {
        Slider savedSlider = sliderRepository.save(slider);
        return sliderMapper.toDto(savedSlider);
    }


}
