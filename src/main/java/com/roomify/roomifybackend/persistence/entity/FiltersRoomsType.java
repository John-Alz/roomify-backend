package com.roomify.roomifybackend.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FiltersRoomsType(LocalDate checkIn, LocalDate checkOut, String roomName, Integer roomCapacity, BigDecimal minPrice, BigDecimal maxPrice, Long amenityId) {
}
