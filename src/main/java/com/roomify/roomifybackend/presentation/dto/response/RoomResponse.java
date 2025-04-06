package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.RoomStatus;

import java.util.List;
import java.util.Set;

public record RoomResponse(Long id, int room_rumber, String room_description, List<String> room_images, RoomStatus room_availability, int room_capacity, float room_price, String room_type_name, Set<AmenityEntity> amenities) {
}
