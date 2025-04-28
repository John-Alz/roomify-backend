package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record BookingResponse(Long id, UserResponse client, LocalDateTime bookingDate, BookingStatus status, LocalDate checkInDate, LocalDate checkOutDate, BigDecimal totalPrice, Set<RoomEntity> rooms) {
}
