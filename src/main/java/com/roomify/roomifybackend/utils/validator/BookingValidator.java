package com.roomify.roomifybackend.utils.validator;

import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.services.exception.CheckInInvalidException;
import com.roomify.roomifybackend.services.exception.CheckOutInvalidException;

import java.time.LocalDate;

public class BookingValidator {

    private BookingValidator() {

    }

    public static void verifyDates(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate.equals(LocalDate.now()) || checkInDate.isBefore(LocalDate.now())) {
            throw new CheckInInvalidException();
        }

        if (checkOutDate.equals(checkInDate) || checkOutDate.isBefore(checkInDate)) {
            throw new CheckOutInvalidException();
        }
    }

}
