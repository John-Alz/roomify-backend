package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.AmenityResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AmenityMapper {

    public AmenityEntity toEntity(SaveAmenityRequest request) {
        return AmenityEntity.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public AmenityResponse toResponse(AmenityEntity entity) {
        return new AmenityResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public List<AmenityResponse> toResponseList(List<AmenityEntity> amenities) {
        return amenities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
