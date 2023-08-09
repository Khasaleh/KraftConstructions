package com.bezkoder.spring.jpa.h2.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
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

    @Column(name = "portfolio_column")
    private Long portfolioColumns;

    @OneToOne(mappedBy = "services", cascade = CascadeType.ALL,orphanRemoval = true)
    private ServiceDetails serviceDetails;


    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Portfolio> portfolios=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "home_page_id",nullable = true)
    private HomePage homePage;


    public Services(String serviceName, String pageName, boolean isActive) {
        this.serviceName = serviceName;
        this.pageName = pageName;
        this.isActive = isActive;
    }


}



