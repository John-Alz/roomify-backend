package com.roomify.roomifybackend.presentation.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record SaveRoomTypeRequest(
        String name,
        String description,
        int beds,
        int bathRooms,
        int meters,
        List<String> images,
        Long quantity_available,
        int capacity,
        BigDecimal price,
        Set<Long> amenities_id) {
}
