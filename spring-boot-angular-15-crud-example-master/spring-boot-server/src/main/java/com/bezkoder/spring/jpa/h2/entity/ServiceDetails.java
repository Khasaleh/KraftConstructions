package com.bezkoder.spring.jpa.h2.entity;

import javax.persistence.*;

@Entity
@Table(name = "servicedetails")
public class ServiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "before_image_url")
    private String beforeImageUrl;
    @Column(name = "after_image_url")
    private String afterImageUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "add_portfolio")
    private Boolean addPortfolio;


    public ServiceDetails() {
        this.serviceName = serviceName;
        this.beforeImageUrl = beforeImageUrl;
        this.afterImageUrl = afterImageUrl;
        this.description = description;
        this.addPortfolio = addPortfolio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAddPortfolio() {
        return addPortfolio;
    }

    public void setAddPortfolio(Boolean addPortfolio) {
        this.addPortfolio = addPortfolio;
    }
}
