package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;

import java.util.Set;

public record ProfileResponse(Long id, String name, String email, Set<RoleEntity> roles) {
}
