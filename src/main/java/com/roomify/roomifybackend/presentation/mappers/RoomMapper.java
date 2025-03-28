package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public RoomEntity toEntity(SaveRoomRequest saveRoomRequest) {
        return  RoomEntity.builder()
                .room_rumber(saveRoomRequest.room_number())
                .room_description(saveRoomRequest.room_description())
                .room_images(saveRoomRequest.room_images())
                .room_availability(saveRoomRequest.room_availability())
                .room_capacity(saveRoomRequest.room_capacity())
                .room_price(saveRoomRequest.room_price())
                .room_type(new RoomTypeEntity(saveRoomRequest.room_type_id(), null, null))
                .build();
    }

    public RoomResponse toResponse(RoomEntity room) {
        return new RoomResponse(
                room.getId(),
                room.getRoom_rumber(),
                room.getRoom_description(),
                room.getRoom_images(),
                room.getRoom_availability(),
                room.getRoom_capacity(),
                room.getRoom_price(),
                room.getRoom_type().getName()
        );
    }

    public List<RoomResponse> toResponseList(List<RoomEntity> rooms) {
        return  rooms.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
