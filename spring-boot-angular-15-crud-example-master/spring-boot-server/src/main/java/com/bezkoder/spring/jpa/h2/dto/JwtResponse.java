package com.bezkoder.spring.jpa.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private String imageUrl;
  private List<String> roles;


  public JwtResponse(String accessToken, Long id, String username, String firstname, String lastname, String email, List<String> roles,String imageUrl) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.firstname=firstname;
    this.lastname=lastname;
    this.email = email;
    this.roles = roles;
    this.imageUrl=imageUrl;
  }

}
