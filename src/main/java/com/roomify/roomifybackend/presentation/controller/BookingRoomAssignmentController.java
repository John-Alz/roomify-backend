package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.request.AssignRoomsRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IBookingRoomAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room_assigment")
@RequiredArgsConstructor
public class BookingRoomAssignmentController {

    private final IBookingRoomAssignmentService iBookingRoomAssignmentService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveBookingRoomAssignment(@RequestBody AssignRoomsRequest assignRoomsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iBookingRoomAssignmentService.saveBookingRoomAssignment(assignRoomsRequest));
    }

}
