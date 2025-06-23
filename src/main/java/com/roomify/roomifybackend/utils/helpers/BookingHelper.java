package com.roomify.roomifybackend.utils.helpers;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class BookingHelper {

    private BookingHelper () {}

    public static BigDecimal calculateTotalPrice(Integer numberOfRooms, BigDecimal priceRoom, Long daysBetween) {
        return priceRoom.multiply(BigDecimal.valueOf(numberOfRooms)).multiply(BigDecimal.valueOf(daysBetween)).add(BigDecimal.valueOf(20000).setScale(0));
    }

    public static String generateUniqueReservationNumber() {
//        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomPart = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        return "RES-" + randomPart;
    }

}
