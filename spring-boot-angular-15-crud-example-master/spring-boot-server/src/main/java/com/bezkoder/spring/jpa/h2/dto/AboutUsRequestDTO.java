package com.bezkoder.spring.jpa.h2.dto;

public class AboutUsRequestDTO {
    private String title;
    private String description;

    public AboutUsRequestDTO() {
    }

    public AboutUsRequestDTO(String title, String description) {
        this.title = title;
        this.description = description;
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