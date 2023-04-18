package com.bezkoder.spring.jpa.h2.Entity;

import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "site_contact_info")
public class SiteContactInfo extends SiteContactInfoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String Address;


    @Size(min = 10, max = 15)
    @Column(nullable = false)
    private String Company_Phones;


    @Size(max = 15)
    @Column(nullable = false)
    private String Fax;

    @Email
    @Column(nullable = false)
    private String Email;



}





