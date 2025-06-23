package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.BookingRoomAssignment;
import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record BookingResponse(
        Long id,
        String bookingNumber,
        UserResponse client,
        String name,
        String lastName,
        String email,
        String phoneNumber,
        RoomTypeResponse RoomType,
        LocalDateTime bookingDate,
        BookingStatus status,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        BigDecimal totalPrice,
        Integer numberOfRoom) {
}

