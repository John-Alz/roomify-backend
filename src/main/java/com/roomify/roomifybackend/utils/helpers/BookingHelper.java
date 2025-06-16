package com.roomify.roomifybackend.utils.helpers;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

public class BookingHelper {

    private BookingHelper () {}

    public static BigDecimal calculateTotalPrice(Integer numberOfRooms, BigDecimal priceRoom, Long daysBetween) {
        return priceRoom.multiply(BigDecimal.valueOf(numberOfRooms)).multiply(BigDecimal.valueOf(daysBetween)).add(BigDecimal.valueOf(20));
    }

    public static String formatCOP(BigDecimal price) {
        Locale colombia = new Locale("es", "CO");
        NumberFormat formatCOP = NumberFormat.getCurrencyInstance(colombia);
        return formatCOP.format(price);
    }

}
