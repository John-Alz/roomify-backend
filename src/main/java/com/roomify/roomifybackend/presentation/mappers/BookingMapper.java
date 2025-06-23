package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    private final UserMapper userMapper;
    private final RoomTypeMapper roomTypeMapper;

    public BookingMapper(UserMapper userMapper, RoomTypeMapper roomTypeMapper) {
        this.userMapper = userMapper;
        this.roomTypeMapper = roomTypeMapper;
    }


    public BookingEntity toBookingEntity(SaveBookingRequest saveBookingRequest, UserEntity userFound, RoomTypeEntity roomTypeFound) {
        return BookingEntity.builder()
                .clientId(userFound)
                .name(saveBookingRequest.name())
                .lastName(saveBookingRequest.lastName())
                .email(saveBookingRequest.email())
                .phoneNumber(saveBookingRequest.phoneNumber())
                .roomType(roomTypeFound)
                .status(saveBookingRequest.status())
                .bookingDate(LocalDateTime.now())
                .checkInDate(saveBookingRequest.checkInDate())
                .checkOutDate(saveBookingRequest.checkOutDate())
                .totalPrice(saveBookingRequest.totalPrice())
                .numberOfRoom(saveBookingRequest.numberOfRoom())
                .build();
    }

    public BookingResponse toResponse(BookingEntity booking) {
        UserResponse user = null;
        if(booking.getClientId() != null) {
            user = userMapper.toResponse(booking.getClientId());
        } else {
            user = new UserResponse(null, null, null, null, null, null, null);
        }
        return new BookingResponse(
                booking.getId(),
                user,
                booking.getName(),
                booking.getLastName(),
                booking.getEmail(),
                booking.getPhoneNumber(),
                roomTypeMapper.toResponse(booking.getRoomType()),
                booking.getBookingDate(),
                booking.getStatus(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getTotalPrice(),
                booking.getNumberOfRoom()
        );
    }

    public List<BookingResponse> roomResponseList(List<BookingEntity> bookings) {
        return bookings.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
