package com.bezkoder.spring.jpa.h2.dto;

public class ServiceDetailsResponseDTO {

    private String serviceName;
    private String description;
    private String beforeImageUrl;
    private String afterImageUrl;
    private Boolean addPortfolio;



    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeforeImageUrl() {
        return beforeImageUrl;
    }

    public void setBeforeImageUrl(String beforeImageUrl) {
        this.beforeImageUrl = beforeImageUrl;
    }

    public String getAfterImageUrl() {
        return afterImageUrl;
    }

    public void setAfterImageUrl(String afterImageUrl) {
        this.afterImageUrl = afterImageUrl;
    }

    public Boolean getAddPortfolio() {
        return addPortfolio;
    }

    public void setAddPortfolio(Boolean addPortfolio) {
        this.addPortfolio = addPortfolio;
    }
}