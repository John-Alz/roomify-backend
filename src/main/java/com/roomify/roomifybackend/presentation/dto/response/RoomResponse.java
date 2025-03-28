package com.roomify.roomifybackend.presentation.dto.response;

import java.util.List;

public record RoomResponse(Long id, int room_rumber, String room_description, List<String> room_images, String room_availability, int room_capacity, float room_price, String room_type_name) {
}
