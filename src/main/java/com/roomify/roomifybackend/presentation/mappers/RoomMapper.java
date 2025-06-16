package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public RoomEntity toEntity(SaveRoomRequest saveRoomRequest, RoomTypeEntity roomTypeFound) {
        return  RoomEntity.builder()
                .room_number(saveRoomRequest.room_number())
                .roomType(roomTypeFound)
                .status(saveRoomRequest.status())
                .lastMaintenance(saveRoomRequest.lastMaintenance())
                .notes(saveRoomRequest.notes())
                .build();
    }

    public RoomResponse toResponse(RoomEntity room) {
        return new RoomResponse(
                room.getId(),
                room.getRoom_number(),
                room.getRoomType(),
                room.getStatus(),
                room.getLastMaintenance(),
                room.getNotes()
        );
    }

    public List<RoomResponse> toResponseList(List<RoomEntity> rooms) {
        return  rooms.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
