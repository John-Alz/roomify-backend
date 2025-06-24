package com.roomify.roomifybackend.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FiltersBooking(
         String numberBooking,
         LocalDate checkInDate,
         LocalDate checkOutDate,
         BigDecimal priceMin,
         Long roomTypeId,
         BookingStatus status
) {
}
