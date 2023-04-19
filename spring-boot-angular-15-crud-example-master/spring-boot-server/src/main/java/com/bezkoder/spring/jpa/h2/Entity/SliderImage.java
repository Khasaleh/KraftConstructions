package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slider_images")
public class SliderImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slider_id")
    private Slider slider;

    private String imageUrl;

    public SliderImage(Slider slider, String imageUrl) {
        this.slider = slider;
        this.imageUrl = imageUrl;
    }


}
