package com.roomify.roomifybackend.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FiltersPayments(
        String numberBooking,
        LocalDate dateFromPayment,
        LocalDate dateToPayment,
        BigDecimal priceMin,
        Long roomTypeId,
        PaymentStatus status
) {
}
