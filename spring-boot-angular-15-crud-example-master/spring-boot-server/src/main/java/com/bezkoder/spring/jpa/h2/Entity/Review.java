package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "your_review")
    private String YourReview;

    @Column(name = "description")
    private String description;


    @Column(name = "work_experience")
    private String WorkExperience;


    @Column(name = "your_name")
    private String YourName;


    @Column(name = "your_email")
    private String YourEmail;


    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.HIDDEN;

}


