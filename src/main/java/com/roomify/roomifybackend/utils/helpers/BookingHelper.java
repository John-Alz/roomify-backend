package com.roomify.roomifybackend.utils.helpers;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;

import java.util.Set;

public class BookingHelper {

    private BookingHelper () {}

    public static float calculateTotalPrice(Set<RoomEntity> rooms) {
        float totalBookingPrice = 0;
        for(RoomEntity room : rooms) {
            totalBookingPrice += room.getRoom_price();
        }
        return totalBookingPrice;
    }

}
