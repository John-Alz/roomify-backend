package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.AmenityResponse;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.implementation.AmenityServiceImpl;
import com.roomify.roomifybackend.services.interfaces.IAmenityservice;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms/amenities")
public class AmenityController {

    @Autowired
    public IAmenityservice amenityService;

    @PostMapping()
    public ResponseEntity<SaveResponse> saveAmenity(@RequestBody SaveAmenityRequest saveAmenityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityService.saveAmenity(saveAmenityRequest));
    }

    @GetMapping
    public ResponseEntity<PageResult<AmenityResponse>> getAmenities(Integer page, Integer size) {
        PageResult<AmenityResponse> amenities = amenityService.getAmenities(page, size);
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityResponse> getAmenityById(@PathVariable Long id) {
        AmenityResponse amenity = amenityService.getAmenityById(id);
        return ResponseEntity.ok(amenity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveResponse> updateAmenity(@PathVariable Long id, @RequestBody SaveAmenityRequest saveAmenityRequest) {
        return ResponseEntity.ok(amenityService.updateAmenity(id, saveAmenityRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteAmenity(@PathVariable Long id) {
        DeleteResponse deleteResponse = amenityService.deleteAmenity(id);
        return ResponseEntity.ok(deleteResponse);
    }


}
