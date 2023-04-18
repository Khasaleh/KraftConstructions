package com.bezkoder.spring.jpa.h2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
	@NotBlank
  private String username;

	@NotBlank
	private String password;

}
