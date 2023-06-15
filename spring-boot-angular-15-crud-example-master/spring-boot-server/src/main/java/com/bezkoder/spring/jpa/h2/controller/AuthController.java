package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.Entity.ERole;
import com.bezkoder.spring.jpa.h2.Entity.Role;
import com.bezkoder.spring.jpa.h2.Entity.User;
import com.bezkoder.spring.jpa.h2.dto.*;
import com.bezkoder.spring.jpa.h2.exception.GenericException;
import com.bezkoder.spring.jpa.h2.jwt.JwtUtils;
import com.bezkoder.spring.jpa.h2.repository.RoleRepository;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;
import com.bezkoder.spring.jpa.h2.service.UserDetailsImpl;
import com.bezkoder.spring.jpa.h2.service.UserDetailsServiceImpl;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 4300)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserDetailsServiceImpl userDetails;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getFirstname(),
            userDetails.getLastname(),
            userDetails.getEmail(),
            roles,
            userDetails.getImageUrl()));
  }

  @PostMapping("/users/create")
  @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(), signUpRequest.getFirstname(), signUpRequest.getLastname(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_PHOTOGRAPHER)
              .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Error: Role is not found.","Incorrect Role"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Error: Role is not found.","Incorrect Role"));
            roles.add(adminRole);
            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_PHOTOGRAPHER)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Error: Role is not found.","Incorrect Role"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  @PostMapping("/users/update/{username}")
  @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
  public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody UpdateUserRequest updateUserRequest){

    User user = userRepository.findByUsername(username)
     .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"User Not Found with username: " + username,"Incorrect username"));

    // Update user properties
    user.setUsername(updateUserRequest.getUsername());
    user.setFirstname(updateUserRequest.getFirstname());
    user.setLastname(updateUserRequest.getLastname());
    user.setEmail(updateUserRequest.getEmail());
    user.setPassword(encoder.encode(updateUserRequest.getPassword()));

    Set<String> strRoles = updateUserRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_PHOTOGRAPHER)
              .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Error: Role is not found.","Incorrect Role"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Error: Role is not found.","Incorrect Role"));
            roles.add(adminRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_PHOTOGRAPHER)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Error: Role is not found.","Incorrect Role"));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
  }
  @DeleteMapping("/users/delete/{username}")
  @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
  public ResponseEntity<?> deleteUser(@PathVariable("username") String username) throws Exception {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"User Not Found with username: " + username,"Incorrect username"));
    userRepository.delete(user);

    return ResponseEntity.ok("User " + username + " has been deleted successfully.");
  }
  @GetMapping("/users")
  @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }
  @PostMapping("/users/uploadprofile/{username}")
  @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
  public ResponseEntity<?> addImageToUser(@PathVariable("username") String username, @RequestBody MultipartFile profileImage) {
      User user = userDetails.addImageToUser(username, profileImage);
      return ResponseEntity.ok(new MessageResponse("Profile image uploaded successfully!"));
  }
}