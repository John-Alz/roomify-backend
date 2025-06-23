package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        BookingEntity booking,
        String preference_id,
        PaymentStatus status,
        BigDecimal amount,
        LocalDateTime created_at
) {
}
