package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.Entity.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String imageUrl;
    private Set<Role> roles = new HashSet<>();
}
