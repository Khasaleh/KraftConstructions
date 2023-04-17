package com.bezkoder.spring.jpa.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class Services {
    @Id
    private String serviceName;
    @Column(name = "page_name")
    private String pageName;
    @Column(name = "is_active")
    private boolean isActive;

    public Services() {
    }

    public Services(String serviceName, String pageName, boolean isActive) {
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
