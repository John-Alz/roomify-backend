package com.roomify.roomifybackend.presentation.controller;


import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    public IRoomService roomService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveRoom(@RequestBody SaveRoomRequest saveRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.saveRoom(saveRoomRequest));
    }

    @GetMapping
    public ResponseEntity<PageResult<RoomResponse>> getRooms(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam boolean orderAsc,
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) Integer roomCapacity,
            @RequestParam(required = false) LocalDate checkIn,
            @RequestParam(required = false) LocalDate checkOut,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Long amenityId){
        PageResult<RoomResponse> rooms = roomService.getAllRooms(page, size, orderAsc, roomType, roomCapacity, checkIn, checkOut, minPrice, maxPrice, amenityId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable("roomId") Long roomId) {
        RoomResponse room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<DeleteResponse> deleteRoomByID(@PathVariable("roomId") Long roomId) {
        DeleteResponse response = roomService.deleteRoom(roomId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable("roomId") Long roomId,
            @RequestBody SaveRoomRequest saveRoomRequest) {
        RoomResponse room = roomService.updateRoom(roomId, saveRoomRequest);
        return ResponseEntity.ok(room);
    }
}
