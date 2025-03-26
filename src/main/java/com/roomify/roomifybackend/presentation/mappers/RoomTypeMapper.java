package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeMapper {

    public RoomTypeEntity toEntity(SaveRoomTypeRequest saveRoomTypeRequest) {
        return RoomTypeEntity.builder()
                .name(saveRoomTypeRequest.name())
                .description(saveRoomTypeRequest.description())
                .build();
    }

}
