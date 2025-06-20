package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveBookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

import java.util.Optional;

public interface IBookingService {

    SaveBookingResponse saveBooking(SaveBookingRequest saveBookingRequest);

    PageResult<BookingResponse> getAllBookings(Integer page, Integer size);

    BookingResponse getBooking(Long bookingId);

    BookingResponse updateBooking(Long bookingId, SaveBookingRequest saveBookingRequest);

    DeleteResponse deleteBooking(Long bookingId);

    void updatePaymentStatus(Long bookingId, String mpStatus);


}
