package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.Entity.Slider;

public interface SliderService {

    SliderDto addSlider(Slider slider);
}
