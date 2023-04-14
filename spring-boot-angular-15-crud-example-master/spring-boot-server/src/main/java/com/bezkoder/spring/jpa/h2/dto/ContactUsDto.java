package com.bezkoder.spring.jpa.h2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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


    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    private Long phonenumber;


    @NotNull
    private String message;

    public ContactUsDto(Long id, String firstname, String lastname, String email, Long phonenumber, String message) {
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

    public Long getPhonenumber() {
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

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
