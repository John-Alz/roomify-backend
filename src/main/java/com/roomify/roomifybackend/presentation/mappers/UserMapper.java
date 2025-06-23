package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.presentation.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getBirthday(),
                user.getRole()
        );
    }

    public List<UserResponse> toResponseList(List<UserEntity> users) {
        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}


