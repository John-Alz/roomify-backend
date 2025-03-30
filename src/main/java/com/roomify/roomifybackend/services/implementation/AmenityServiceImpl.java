package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.repository.AmenityRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.AmenityMapper;
import com.roomify.roomifybackend.services.interfaces.IAmenityservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements IAmenityservice {

    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    @Override
    public SaveResponse saveAmenity(SaveAmenityRequest amenityRequest) {
        amenityRepository.save(amenityMapper.toEntity(amenityRequest));
        return new SaveResponse("Amedidad creada existosamente", LocalDate.now());
    }
}
