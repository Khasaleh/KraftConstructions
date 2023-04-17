package com.bezkoder.spring.jpa.h2.dto;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class ContactUsDto {


    @NotNull
    private Long id;


    @NotNull
    private String firstname;


    @NotNull
    private String lastname;

    @NotNull
    @Email
    private String email;



    @Size(min = 10, max = 15)
    private String phonenumber;


    @NotNull
    private String message;

    public ContactUsDto(Long id, String firstname, String lastname, String email, String phonenumber, String message) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.message = message;
    }

    public ContactUsDto() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
