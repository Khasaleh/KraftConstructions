package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sliders")
public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SliderImage> images = new ArrayList<>();

    public List<String> getImageUrls() {
        List<String> imageUrls = new ArrayList<>();
        for (SliderImage image : images) {
            imageUrls.add(image.getImageUrl());
        }
        return imageUrls;
    }

    public void addImage(String imageUrl) {
        images.add(new SliderImage(this, imageUrl));
    }


}


