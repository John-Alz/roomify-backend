package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
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
                .beds(saveRoomTypeRequest.beds())
                .bathRooms(saveRoomTypeRequest.bathRooms())
                .meters(saveRoomTypeRequest.meters())
                .images(saveRoomTypeRequest.images())
                .quantity_available(saveRoomTypeRequest.quantity_available())
                .capacity(saveRoomTypeRequest.capacity())
                .price(saveRoomTypeRequest.price())
                .amenities(saveRoomTypeRequest.amenities_id().stream()
                        .map(id -> new AmenityEntity(id,null, null, null))
                        .collect(Collectors.toSet()))
                .build();
    }

    public RoomTypeResponse toResponse(RoomTypeEntity roomTypeEntity) {
        return new RoomTypeResponse(
                roomTypeEntity.getId(),
                roomTypeEntity.getName(),
                roomTypeEntity.getDescription(),
                roomTypeEntity.getBeds(),
                roomTypeEntity.getBathRooms(),
                roomTypeEntity.getMeters(),
                roomTypeEntity.getImages(),
                roomTypeEntity.getQuantity_available(),
                roomTypeEntity.getCapacity(),
                roomTypeEntity.getPrice(),
                roomTypeEntity.getAmenities()
        );
    }

    public List<RoomTypeResponse> toListResponse(List<RoomTypeEntity> roomTypes) {
        return roomTypes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
