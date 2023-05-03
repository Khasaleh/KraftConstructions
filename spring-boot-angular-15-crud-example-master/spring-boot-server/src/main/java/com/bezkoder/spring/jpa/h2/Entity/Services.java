package com.bezkoder.spring.jpa.h2.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_name",nullable = false)
    private String serviceName;
    @Column(name = "page_name",nullable = false)
    private String pageName;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "services", cascade = CascadeType.ALL,orphanRemoval = true)
    private ServiceDetails serviceDetails;






    public Services(String serviceName, String pageName, boolean isActive) {
        this.serviceName = serviceName;
        this.pageName = pageName;
        this.isActive = isActive;
    }



}



