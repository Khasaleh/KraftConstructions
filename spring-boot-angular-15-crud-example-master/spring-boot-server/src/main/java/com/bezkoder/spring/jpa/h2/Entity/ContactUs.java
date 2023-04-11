package com.bezkoder.spring.jpa.h2.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ContactUs")
public class ContactUs {

    @Id
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private Integer phonenumber;


    @Column(name = "message")
    private String message;

    // Getters and setters, constructors, etc.





}