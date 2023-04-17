package com.bezkoder.spring.jpa.h2.Entity;

import com.bezkoder.spring.jpa.h2.dto.SiteContactInfoDTO;

import javax.persistence.*;

@Entity
@Table(name = "site_contact_info")
public class SiteContactInfo extends SiteContactInfoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Address;

    @Column(nullable = false)
    private String Company_Phones;

    @Column(nullable = false)
    private String Fax;

    @Column(nullable = false)
    private String Email;


    public SiteContactInfo() {
    }

    public SiteContactInfo(Long id, String address, String company_Phones, String fax, String email) {
        this.id = id;
        Address = address;
        Company_Phones = company_Phones;
        Fax = fax;
        Email = email;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return Address;
    }

    public String getCompany_Phones() {
        return Company_Phones;
    }

    public String getFax() {
        return Fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCompany_Phones(String company_Phones) {
        Company_Phones = company_Phones;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public void setEmail(String email) {
        Email = email;
    }
}




