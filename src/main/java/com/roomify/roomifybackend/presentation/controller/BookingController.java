package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService bookingService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveBooking(@RequestBody SaveBookingRequest saveBookingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.saveBooking(saveBookingRequest));
    }

    @GetMapping
    public ResponseEntity<PageResult<BookingResponse>> getBooking(Integer page, Integer size) {
        PageResult<BookingResponse> bookings = bookingService.getAllBookings(page, size);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id, @RequestBody SaveBookingRequest saveBookingRequest) {
        BookingResponse booking = bookingService.updateBooking(id, saveBookingRequest);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteBooking(@PathVariable Long id) {
        DeleteResponse deleteResponse = bookingService.deleteBooking(id);
        return ResponseEntity.ok(deleteResponse);
    }
}
