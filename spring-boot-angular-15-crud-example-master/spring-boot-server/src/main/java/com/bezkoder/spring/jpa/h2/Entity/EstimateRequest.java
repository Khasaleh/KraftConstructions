package com.bezkoder.spring.jpa.h2.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "estimate_requests")
public class EstimateRequest {
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

    @ElementCollection
    @CollectionTable(name = "requested_services", joinColumns = @JoinColumn(name = "estimate_request_id"))
    @Column(name = "service")
    private List<String> requestedServices = new ArrayList<>();

    @Column(name = "budget")
    private String budget;

    @Column(name = "project_description")
    @Length(max = 1500)
    private String projectDescription;
    @Column(name = "aboutus")
    private String aboutUs;
}
