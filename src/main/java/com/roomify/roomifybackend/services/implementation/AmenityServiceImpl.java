package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.repository.AmenityRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.AmenityResponse;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.AmenityMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IAmenityservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public PageResult<AmenityResponse> getAmenities(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<AmenityEntity> amenityPage = amenityRepository.findAll(paging);
        List<AmenityResponse> amenitiesList = amenityMapper.toResponseList(amenityPage.getContent());
        return new PageResult<>(
                amenitiesList,
                amenityPage.getNumber(),
                amenityPage.getSize(),
                amenityPage.getTotalPages(),
                amenityPage.getTotalElements()
        );
    }

    @Override
    public AmenityResponse getAmenityById(Long amenityId) {

        AmenityEntity amenityFound = amenityRepository.findById(amenityId).orElse(null);

        if (amenityFound == null) {
            throw new NoExistException("Amenidad no encontrada");
        }

        return amenityMapper.toResponse(amenityFound);
    }

    @Override
    public SaveResponse updateAmenity(Long amenityId, SaveAmenityRequest amenityRequest) {
        AmenityEntity amenityFound = amenityRepository.findById(amenityId).orElse(null);

        if (amenityFound == null) {
            throw new NoExistException("Amenidad no encontrada");
        }

        amenityFound.setName(amenityRequest.name());
        amenityFound.setDescription(amenityRequest.description());

        amenityRepository.save(amenityFound);

        return new SaveResponse("Amenidad actualizada", LocalDate.now());
    }

    @Override
    public DeleteResponse deleteAmenity(Long amenityId) {
        AmenityEntity amenityFound = amenityRepository.findById(amenityId).orElse(null);

        if (amenityFound == null) {
            throw new NoExistException("Amenidad no encontrada");
        }

        amenityRepository.deleteById(amenityId);

        return new DeleteResponse("Amenidad eliminada", LocalDate.now());
    }
}
