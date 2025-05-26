package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;

import java.time.LocalDate;
import java.util.Set;

public record ProfileResponse(Long id, String name, String lastName, String email, String phoneNumber, LocalDate birthday, Set<RoleEntity> roles) {
}
