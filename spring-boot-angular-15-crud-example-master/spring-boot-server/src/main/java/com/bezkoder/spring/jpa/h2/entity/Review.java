package com.bezkoder.spring.jpa.h2.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull(message = "YourReview field cannot be null")
        @Column(name = "your_review")
        private String YourReview;

        @NotNull(message = "WorkExperience field cannot be null")
        @Column(name = "work_experience")
        private String WorkExperience;

        @NotNull(message = "YourName field cannot be null")
        @Column(name = "your_name")
        private String YourName;

        @NotNull(message = "YourEmail field cannot be null")
        @Column(name = "your_email")
        private String YourEmail;

        @NotNull(message = "approvalStatus field cannot be null")
        @Enumerated(EnumType.STRING)
        private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;


        public ApprovalStatus getApprovalStatus() {
                return approvalStatus;
        }

        public void setApprovalStatus(ApprovalStatus approvalStatus) {
                this.approvalStatus = approvalStatus;
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

        public String  getYourName() {
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


