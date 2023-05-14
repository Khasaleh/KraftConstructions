package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.User;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


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
  public User addImageToUser(String username, MultipartFile image) throws Exception {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new Exception("User Not Found with username: " + username));
    String imageUrl = null;
    try {
      imageUrl = saveImage(image);
    } catch (IOException e) {
      e.printStackTrace();
    }
    user.setImageUrl(imageUrl);
    return userRepository.save(user);
  }
  public String saveImage(MultipartFile image) throws IOException {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
    Path uploadPath = Paths.get("uploads/profileimages");
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    InputStream inputStream = image.getInputStream();
    Path filePath = uploadPath.resolve(fileName);
    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    return filePath.toString();
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
