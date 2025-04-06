package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public BookingEntity toBookingEntity(SaveBookingRequest saveBookingRequest, UserEntity  userFound, Set<RoomEntity> roomsFound) {
        return BookingEntity.builder()
                .clientId(userFound)
                .status(saveBookingRequest.status())
                .bookingDate(LocalDateTime.now())
                .checkInDate(saveBookingRequest.checkInDate())
                .checkOutDate(saveBookingRequest.checkOutDate())
                .rooms(roomsFound)
                .build();
    }

    public BookingResponse toResponse(BookingEntity booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getClientId(),
                booking.getBookingDate(),
                booking.getStatus(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getTotalPrice(),
                booking.getRooms()
        );
    }

    public List<BookingResponse> roomResponseList(List<BookingEntity> bookings) {
        return bookings.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
