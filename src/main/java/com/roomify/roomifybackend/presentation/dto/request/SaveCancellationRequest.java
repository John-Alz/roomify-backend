package com.roomify.roomifybackend.presentation.dto.request;


public record SaveCancellationRequest(
        Long bookingId,
        Long userId,
        String reasonForCancellation
) {
}
