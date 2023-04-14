package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.entity.ApprovalStatus;

import javax.validation.constraints.NotNull;

public class ReviewDto {
    private Long id;
    @NotNull(message = "YourReview field cannot be null")
    private String YourReview;
    @NotNull(message = "WorkExperience field cannot be null")
    private String WorkExperience;
    @NotNull(message = "YourName field cannot be null")
    private String YourName;
    @NotNull(message = "YourEmail field cannot be null")
    private String YourEmail;

    private ApprovalStatus approvalStatus;

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

//    public ReviewDto(Long id) {
//        this.id = id;
//    }

    public ReviewDto(Long id, String yourReview, String workExperience, String yourName, String yourEmail) {
        this.id = id;
        YourReview = yourReview;
        WorkExperience = workExperience;
        YourName = yourName;
        YourEmail = yourEmail;
    }

    public ReviewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYourReview() {
        return YourReview;
    }

    public void setYourReview(String yourReview) {
        YourReview = yourReview;
    }

    public String getWorkExperience() {
        return WorkExperience;
    }

    public void setWorkExperience(String workExperience) {
        WorkExperience = workExperience;
    }

    public String getYourName() {
        return YourName;
    }

    public void setYourName(String yourName) {
        YourName = yourName;
    }

    public String getYourEmail() {
        return YourEmail;
    }

    public void setYourEmail(String yourEmail) {
        YourEmail = yourEmail;
    }
}
