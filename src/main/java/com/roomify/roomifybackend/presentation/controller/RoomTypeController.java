package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room-type")
public class RoomTypeController {

    @Autowired
    private IRoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveRoomType(@RequestBody SaveRoomTypeRequest saveRoomTypeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomTypeService.saveRoomType(saveRoomTypeRequest));
    }
}
