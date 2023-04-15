package com.bezkoder.spring.jpa.h2.dto;

public class AboutUsResponseDTO {
    private Long id;
    private String title;
    private String description;

    public AboutUsResponseDTO() {}

    public AboutUsResponseDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}