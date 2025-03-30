package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.RoomTypeResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomTypeMapper {

    public RoomTypeEntity toEntity(SaveRoomTypeRequest saveRoomTypeRequest) {
        return RoomTypeEntity.builder()
                .name(saveRoomTypeRequest.name())
                .description(saveRoomTypeRequest.description())
                .build();
    }

    public RoomTypeResponse toResponse(RoomTypeEntity roomTypeEntity) {
        return new RoomTypeResponse(
                roomTypeEntity.getId(),
                roomTypeEntity.getName(),
                roomTypeEntity.getDescription()
        );
    }

    public List<RoomTypeResponse> toListResponse(List<RoomTypeEntity> roomTypes) {
        return roomTypes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
