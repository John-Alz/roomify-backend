package com.roomify.roomifybackend.presentation.advice;

import java.time.LocalDate;

public record ExceptionResponse(String message, LocalDate date) {
}
