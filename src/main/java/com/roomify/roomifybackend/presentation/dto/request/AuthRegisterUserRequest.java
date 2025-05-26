package com.roomify.roomifybackend.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AuthRegisterUserRequest(@NotBlank String username, @NotBlank String lastName, @NotBlank String email, String phoneNumber, LocalDate birthday, @NotBlank String password, @Valid AuthCreateRoleRequest roleRequest) {
}
