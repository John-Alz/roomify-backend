package com.roomify.roomifybackend.presentation.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"email", "message", "jwt", "status"}) // Darle un orden al response
public record AuthResponse(String email, String message, String jwt, boolean status) {
}
