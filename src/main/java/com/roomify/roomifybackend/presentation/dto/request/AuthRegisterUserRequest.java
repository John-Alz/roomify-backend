package com.roomify.roomifybackend.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterUserRequest(@NotBlank String username, @NotBlank String email, @NotBlank String password, @Valid AuthCreateRoleRequest roleRequest) {
}
