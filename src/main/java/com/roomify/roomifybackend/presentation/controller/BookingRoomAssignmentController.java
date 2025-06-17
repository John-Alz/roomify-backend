package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.request.AssignmentRoomsRequest;
import com.roomify.roomifybackend.presentation.dto.response.AssignmentRoomsResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IBookingRoomAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room_assigment")
@RequiredArgsConstructor
public class BookingRoomAssignmentController {

    private final IBookingRoomAssignmentService iBookingRoomAssignmentService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveBookingRoomAssignment(@RequestBody AssignmentRoomsRequest assignRoomsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iBookingRoomAssignmentService.saveBookingRoomAssignment(assignRoomsRequest));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<AssignmentRoomsResponse> getBookingRoomAssignment(@PathVariable Long bookingId) {
        return ResponseEntity.ok(iBookingRoomAssignmentService.getBookingRoomAssignmentByBookingId(bookingId));
    }

}
