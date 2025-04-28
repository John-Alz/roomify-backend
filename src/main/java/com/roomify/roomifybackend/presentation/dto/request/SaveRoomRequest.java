package com.roomify.roomifybackend.presentation.dto.request;

import com.roomify.roomifybackend.persistence.entity.RoomStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record SaveRoomRequest(int room_number, String room_name, String room_description, int rooms, int bathRooms, List<String> room_images, RoomStatus room_availability, int room_capacity, BigDecimal room_price, Long room_type_id, Set<Long> amenities_id) {
}
