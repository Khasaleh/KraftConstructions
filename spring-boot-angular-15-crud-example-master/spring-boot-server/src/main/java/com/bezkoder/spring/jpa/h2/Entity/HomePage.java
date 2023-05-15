package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "homepage")
public class HomePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "about_us_link")
    private String aboutusLink;

    @Column(name = "about_us_description")
    @Length(max = 9999)
    private String aboutusDescription;

    @Column(name = "about_us_video_url")
    private String aboutusVideoUrl;

    @OneToMany(mappedBy = "homePage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Services> services;
}


