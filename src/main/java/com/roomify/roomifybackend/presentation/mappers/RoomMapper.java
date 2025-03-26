package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import org.springframework.stereotype.Component;

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

}
