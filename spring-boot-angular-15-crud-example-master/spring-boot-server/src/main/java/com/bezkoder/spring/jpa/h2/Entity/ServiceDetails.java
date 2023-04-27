package com.bezkoder.spring.jpa.h2.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @JoinColumn(name = "services_id")
    private Services services;

    @NotBlank
    @Column(name = "before_image_url")
    private String beforeImageUrl;

    @NotBlank
    @Column(name = "after_image_url")
    private String afterImageUrl;

    @NotBlank
    @Column(name = "description")
    private String description;


    @Column(name = "add_portfolio")
    private boolean addPortfolio;


    @Column(name = "author")
    private String author;

    @Column(name = "update_date")
    private LocalDateTime updateDate;



    // other fields, constructors, and getters/setters
}