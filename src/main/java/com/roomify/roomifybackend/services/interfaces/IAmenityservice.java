package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IAmenityservice {

    SaveResponse saveAmenity(SaveAmenityRequest amenityRequest);

}
