package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.persistence.entity.FiltersRoomsType;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomTypeResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/rooms/types")
public class RoomTypeController {

    @Autowired
    private IRoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<SaveResponse> saveRoomType(@RequestBody SaveRoomTypeRequest saveRoomTypeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomTypeService.saveRoomType(saveRoomTypeRequest));
    }

    @GetMapping
    public ResponseEntity<PageResult<RoomTypeResponse>> getAllRoomTypes(Integer page, Integer size,@ModelAttribute FiltersRoomsType filtersRoomsType) {
        PageResult<RoomTypeResponse> roomTypes = roomTypeService.getAllRoomType(page, size, filtersRoomsType);
        return ResponseEntity.ok(roomTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeResponse> getRoomTypeById(@PathVariable("id") Long id) {
        RoomTypeResponse room = roomTypeService.getRoomType(id);
        return ResponseEntity.ok(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveResponse> updateRoomType(
            @PathVariable("id") Long id,
            @RequestBody SaveRoomTypeRequest saveRoomTypeRequest
            ) {
        return ResponseEntity.ok(roomTypeService.updateRoomType(id, saveRoomTypeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteRoomType(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roomTypeService.deleteRoomType(id));
    }
}
