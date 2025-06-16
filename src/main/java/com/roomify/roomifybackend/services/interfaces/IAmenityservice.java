package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.AmenityResponse;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IAmenityservice {

    SaveResponse saveAmenity(SaveAmenityRequest amenityRequest);

    PageResult<AmenityResponse> getAmenities(Integer page, Integer size);

    AmenityResponse getAmenityById(Long amenityId);

    SaveResponse updateAmenity(Long amenityId, SaveAmenityRequest amenityRequest);

    DeleteResponse deleteAmenity(Long amenityId);

}
