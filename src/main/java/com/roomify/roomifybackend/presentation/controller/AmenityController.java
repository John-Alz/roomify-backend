package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.implementation.AmenityServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms/amenities")
public class AmenityController {

    @Autowired
    public AmenityServiceImpl amenityService;

    @PostMapping()
    public ResponseEntity<SaveResponse> saveAmenity(@RequestBody SaveAmenityRequest saveAmenityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityService.saveAmenity(saveAmenityRequest));
    }



}
