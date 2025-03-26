package com.roomify.roomifybackend.presentation.controller;


import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    public IRoomService roomService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveRoom(@RequestBody SaveRoomRequest saveRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.saveRoom(saveRoomRequest));
    }
}
