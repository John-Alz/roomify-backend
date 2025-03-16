package com.roomify.roomifybackend.presentation.dto;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public record ProfileResponse(Long id, String name, String email, Set<RoleEntity> roles) {
}
