package com.roomify.roomifybackend.presentation.dto.response;

import java.time.LocalDate;

public record SaveBookingResponse(String message, Long bookingId, LocalDate time) {
}
