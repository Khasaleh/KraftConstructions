package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.Entity.Reference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareersApplicationResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private Long zip;
    private String workExperience;
    private String jobType;
    private String workRestrictions;
    private String hoursRestrictions;
    private String skills;
    private List<Reference> references;
    private String otherNotes;
    private String resumeUrl;
}
