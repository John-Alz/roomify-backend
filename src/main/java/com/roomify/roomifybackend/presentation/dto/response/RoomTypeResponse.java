package com.roomify.roomifybackend.presentation.dto.response;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record RoomTypeResponse(
        Long id,
        String name,
        String description,
        int beds,
        int bathRooms,
        int meters,
        List<String> images,
        int quantity_available,
        int capacity,
        BigDecimal price,
        Set<AmenityEntity> amenities) {
}
