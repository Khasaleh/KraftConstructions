package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.User;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  public User updateUser(User updatedUser) throws Exception {
    User user = userRepository.findById(updatedUser.getId())
            .orElseThrow(() -> new Exception("User Not Found with id: " + updatedUser.getId()));

    user.setUsername(updatedUser.getUsername());
    user.setEmail(updatedUser.getEmail());
    user.setPassword(updatedUser.getPassword());
    user.setRoles(updatedUser.getRoles());

    return userRepository.save(user);
  }

}
