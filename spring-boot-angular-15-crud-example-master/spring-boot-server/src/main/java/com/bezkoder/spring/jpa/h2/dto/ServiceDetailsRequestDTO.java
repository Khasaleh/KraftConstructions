package com.bezkoder.spring.jpa.h2.dto;

public class ServiceDetailsRequestDTO {
    private Long id;
    private String serviceName;
    private String description;
    private String beforeImageUrl;
    private String afterImageUrl;
    private Boolean addPortfolio;



    public ServiceDetailsRequestDTO(String serviceName, String description, String beforeImageUrl, String afterImageUrl, Boolean addPortfolio) {
        this.serviceName = serviceName;
        this.description = description;
        this.beforeImageUrl = beforeImageUrl;
        this.afterImageUrl = afterImageUrl;
        this.addPortfolio = addPortfolio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceDetailsRequestDTO() {

    }

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