package com.bezkoder.spring.jpa.h2.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "page_name")
    private String pageName;
    @Column(name = "is_active")
    private boolean isActive;
//    @Column(name = "before_image_url")
//    private String beforeImageUrl;
//    @Column(name = "after_image_url")
//    private String afterImageUrl;
//    @Column(name = "description")
//    private String description;
//    @Column(name = "add_portfolio")
//    private Boolean addPortfolio;
//    @Column(name = "author")
//    private String author;
//    @Column(name = "updatedon")
//    private LocalDateTime updatedOn;



    public Services(String serviceName, String pageName, boolean isActive) {
        this.serviceName = serviceName;
        this.pageName = pageName;
        this.isActive = isActive;
    }

//    public Services(String serviceName, String beforeImageUrl, String afterImageUrl, String description, Boolean addPortfolio, LocalDateTime updatedOn) {
//        this.serviceName = serviceName;
//        this.beforeImageUrl = beforeImageUrl;
//        this.afterImageUrl = afterImageUrl;
//        this.description = description;
//        this.addPortfolio = addPortfolio;
//        this.updatedOn = updatedOn;
//    }
}



