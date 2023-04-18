package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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



    @NotNull
    @Size(min = 10, max = 15)
    private String phonenumber;


    @NotNull
    private String message;

}