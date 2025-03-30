package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    public AmenityEntity toEntity(SaveAmenityRequest request) {
        return AmenityEntity.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

}
