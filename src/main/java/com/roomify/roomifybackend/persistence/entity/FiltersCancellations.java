package com.roomify.roomifybackend.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FiltersCancellations(
         String numberBooking,
         LocalDate dateFromCancellation,
         LocalDate dateToCancellation,
         BigDecimal priceMin,
         Long roomTypeId
) {
}
