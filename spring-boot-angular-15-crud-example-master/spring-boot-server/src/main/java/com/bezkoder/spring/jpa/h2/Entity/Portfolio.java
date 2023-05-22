package com.bezkoder.spring.jpa.h2.Entity;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;


}

