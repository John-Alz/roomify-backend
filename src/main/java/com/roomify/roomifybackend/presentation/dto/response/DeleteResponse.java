package com.roomify.roomifybackend.presentation.dto.response;

import java.time.LocalDate;

public record DeleteResponse(String message, LocalDate time) {
}
