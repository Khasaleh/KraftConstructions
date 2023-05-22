package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.User;
import com.bezkoder.spring.jpa.h2.dto.UserResponse;

public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setPassword(user.getPassword());
        userResponse.setImageUrl(user.getImageUrl());
        userResponse.setRoles(user.getRoles());
        return userResponse;
    }
}