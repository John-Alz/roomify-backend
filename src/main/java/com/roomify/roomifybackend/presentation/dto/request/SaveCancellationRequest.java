package com.roomify.roomifybackend.presentation.dto.request;

import java.time.LocalDate;

public record SaveCancellationRequest(
        Long bookingId,
        String reasonForCancellation
) {
}
