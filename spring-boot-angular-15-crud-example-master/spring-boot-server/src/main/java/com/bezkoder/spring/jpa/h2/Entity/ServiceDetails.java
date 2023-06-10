package com.bezkoder.spring.jpa.h2.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service_details")
public class ServiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_id")
    private Services services;

    @Column(name = "before_image_url",nullable = false)
    private String beforeImageUrl;

    @Column(name = "after_image_url",nullable = false)
    private String afterImageUrl;

    @Column(name = "before_image_title",nullable = false)
    private String beforeImageTitle;

    @Column(name = "after_image_title",nullable = false)
    private String afterImageTitle;

    @Column(name = "description")
    private String description;


    @Column(name = "add_portfolio")
    private boolean addPortfolio;


    @Column(name = "author")
    private String author;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}