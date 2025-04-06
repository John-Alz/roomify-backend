package com.roomify.roomifybackend.presentation.dto.request;

import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.time.LocalDate;
import java.util.Set;

public record SaveBookingRequest(Long clientId, BookingStatus status, LocalDate checkInDate, LocalDate checkOutDate, Set<Long> rooms) {
}
