package com.roomify.roomifybackend.presentation.dto.request;

import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record SaveBookingRequest(
        Long clientId,
        Long roomTypeId,
        BookingStatus status,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        BigDecimal totalPrice,
        Integer numberOfRoom) {
}
