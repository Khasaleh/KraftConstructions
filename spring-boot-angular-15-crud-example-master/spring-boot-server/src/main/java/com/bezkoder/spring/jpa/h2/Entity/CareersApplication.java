package com.bezkoder.spring.jpa.h2.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "career_applications")
public class CareersApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private Long zip;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "work_restrictions")
    private String workRestrictions;

    @Column(name = "hours_restrictions")
    private String hoursRestrictions;

    @Column(name="skills")
    private String skills;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private List<Reference> references;

    @Column(name = "other_notes")
    private String otherNotes;

    @Column(name = "resume_url")
    private String resumeUrl;
}
