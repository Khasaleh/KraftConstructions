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
@Table(name = "contact_us")
public class ContactUs {

    @Id
    @GeneratedValue
    private Long id;



    @Column(name = "firstname")
    private String firstname;


    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;


    @Column(name = "phoneNumber")
    private String phonenumber;


    @Column(name = "message")
    private String message;

    @Column(name = "createdDate")
    private String createdDate;



}