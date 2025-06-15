package com.roomify.roomifybackend.utils.helpers;

import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeUtils {

    private DateRangeUtils() {}

    public static List<LocalDate> generateDates (LocalDate checkInDate, LocalDate checkOutDate) {

        List<LocalDate> dates = new ArrayList<>();

        while (checkInDate.isBefore(checkOutDate)) {
            dates.add(checkInDate);
            checkInDate = checkInDate.plusDays(1);
        }

        return dates;
    }

}
