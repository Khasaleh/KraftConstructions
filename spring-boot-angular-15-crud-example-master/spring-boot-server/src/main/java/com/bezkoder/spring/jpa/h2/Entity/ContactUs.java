package com.bezkoder.spring.jpa.h2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact_us")
public class ContactUs {

    @Id
    private Integer id;

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


    public ContactUs(Integer id, String firstname, String lastname, String email, Integer phonenumber, String message) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.message = message;
    }

    public ContactUs() {
    }

    public Integer getId() {
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

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Integer id) {
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

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}