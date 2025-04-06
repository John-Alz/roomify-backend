package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record BookingResponse(Long id, UserEntity client, LocalDateTime bookingDate, BookingStatus status, LocalDate checkInDate, LocalDate checkOutDate, float totalPrice, Set<RoomEntity> rooms) {
}
