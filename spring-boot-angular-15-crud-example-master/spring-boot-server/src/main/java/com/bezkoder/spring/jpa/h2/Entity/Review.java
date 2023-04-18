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


    @Column(name = "work_experience")
    private String WorkExperience;


    @Column(name = "your_name")
    private String YourName;


    @Column(name = "your_email")
    private String YourEmail;


    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.HIDDEN;


//    public ApprovalStatus getApprovalStatus() {
//        return approvalStatus;
//    }
//
//    public void setApprovalStatus(ApprovalStatus approvalStatus) {
//        this.approvalStatus = approvalStatus;
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getYourReview() {
//        return YourReview;
//    }
//
//    public void setYourReview(String yourReview) {
//        YourReview = yourReview;
//    }
//
//    public String getWorkExperience() {
//        return WorkExperience;
//    }
//
//    public void setWorkExperience(String workExperience) {
//        WorkExperience = workExperience;
//    }
//
//    public String getYourName() {
//        return YourName;
//    }
//
//    public void setYourName(String yourName) {
//        YourName = yourName;
//    }
//
//    public String getYourEmail() {
//        return YourEmail;
//    }
//
//    public void setYourEmail(String yourEmail) {
//        YourEmail = yourEmail;
//    }
}


