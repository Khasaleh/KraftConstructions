package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SiteContactInfoDto {

    private Long id;


    private String Address;


    @Size(min = 10, max = 15)
    private String Company_Phones;


    @Size(max = 15)
    private String Fax;

    @Email
    private String Email;

}
