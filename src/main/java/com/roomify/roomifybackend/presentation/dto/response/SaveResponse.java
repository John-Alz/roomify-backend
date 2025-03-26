package com.roomify.roomifybackend.presentation.dto.response;

import java.time.LocalDate;

public record SaveResponse(String message, LocalDate time) {
}
