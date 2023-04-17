package com.bezkoder.spring.jpa.h2.dto;

public class ServicesResponseDTO {

    private String serviceName;
    private String pageName;
    private boolean isActive;

    public ServicesResponseDTO() {
    }

    public ServicesResponseDTO(String serviceName, String pageName, boolean isActive) {
        this.serviceName = serviceName;
        this.pageName = pageName;
        this.isActive = isActive;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}


