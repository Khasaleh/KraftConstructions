package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;


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


    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;


    @NotNull
    @Column(name = "phoneNumber")
    private String phonenumber;

    @NotNull
    @Column(name = "message")
    private String message;

    // Getters and setters, constructors, etc.


}