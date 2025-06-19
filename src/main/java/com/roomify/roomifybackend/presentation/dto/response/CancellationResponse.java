package com.roomify.roomifybackend.presentation.dto.response;

import java.time.LocalDate;

public record CancellationResponse(
        Long id,
        BookingResponse boooking,
        LocalDate dateOfCancellation,
        String reasonForCancellation
) {
}
