package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.Slider;
import com.bezkoder.spring.jpa.h2.dto.SliderDto;
import com.bezkoder.spring.jpa.h2.mapper.SliderMapper;
import com.bezkoder.spring.jpa.h2.repository.SliderRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SliderServiceImpl implements SliderService {
    @Autowired
    private final SliderRepository sliderRepository;
    @Autowired
    private final SliderMapper sliderMapper;

    public SliderServiceImpl(SliderRepository sliderRepository, SliderMapper sliderMapper) {
        this.sliderRepository = sliderRepository;
        this.sliderMapper = sliderMapper;
    }

    @Override
    public SliderDto addSlider(MultipartFile[] images) throws IOException {

        Slider slider = new Slider();


        for (MultipartFile image : images) {
            String imageUrl = saveImage(image);
            slider.addImage(imageUrl);
        }


        Slider savedSlider = sliderRepository.save(slider);


        return sliderMapper.toDto(savedSlider);
    }

    @Override
    public List<SliderDto> getAllSliders() {

        List<Slider> sliders = sliderRepository.findAll();
        return sliderMapper.toDtos(sliders);
    }

    private String saveImage(MultipartFile image) throws IOException {

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        File file=new File("uploads/testimonials/slider/" + fileName);

        FileUtils.writeByteArrayToFile(file, image.getBytes());


        return file.getPath();
    }
    @Override
    public void deleteSlider(Long sliderId) {
        sliderRepository.deleteById(sliderId);
    }
}






