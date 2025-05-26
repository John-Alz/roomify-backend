package com.roomify.roomifybackend.persistence.entity;

import java.math.BigDecimal;

public record FiltersRoomsType(String roomName, Integer roomCapacity, BigDecimal minPrice, BigDecimal maxPrice, Long amenityId) {
}
