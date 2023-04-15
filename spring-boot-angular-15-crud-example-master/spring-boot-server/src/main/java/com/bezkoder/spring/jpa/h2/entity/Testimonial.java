package com.bezkoder.spring.jpa.h2.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "testimonials")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "page field cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "page")
    private TestimonialPage page;

    @NotNull(message = "imageUrl field cannot be null")
    @Column(name = "image_url")
    private String imageUrl;

    public Testimonial() {
        this.id = id;
        this.page = page;
    }


    // constructor, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestimonialPage getPage() {
        return page;
    }

    public void setPage(TestimonialPage page) {
        this.page = page;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}






