package com.bezkoder.spring.jpa.h2.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "homepage_about_us")
public class HomePageAboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    @Length(max = 9999)
    private String description;

    @Column(name = "video_url")
    private String videoUrl;


}