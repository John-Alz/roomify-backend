package com.roomify.roomifybackend.presentation.dto.request;

import java.util.List;
import java.util.Set;

public record SaveRoomRequest(int room_number, String room_description, List<String> room_images, String room_availability, int room_capacity, float room_price, Long room_type_id, Set<Long> amenities_id) {
}
