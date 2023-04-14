package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.entity.TestimonialPage;

import javax.validation.constraints.NotNull;

public class TestimonialDto {
    @NotNull(message = "id field cannot be null")
    private Long id;
    @NotNull(message = "page field cannot be null")
    private TestimonialPage page;
    @NotNull(message = "imageUrl field cannot be null")
    private String imageUrl;

    public TestimonialDto() {
    }

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