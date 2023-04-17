package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @NotNull(message = "approvalStatus field cannot be null")
    private ApprovalStatus approvalStatus;


}
