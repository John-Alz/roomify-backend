package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;

import java.time.LocalDate;
import java.util.Set;

public record UserResponse(Long id, String name, String lastName, String phoneNumber, String email, LocalDate birthday, RoleEntity role) {
}
