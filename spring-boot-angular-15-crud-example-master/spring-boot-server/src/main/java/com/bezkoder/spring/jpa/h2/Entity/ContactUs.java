package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

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


    public ContactUs(Long id, String firstname, String lastname, String email, String phonenumber, String message) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.message = message;
    }

    public ContactUs() {
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