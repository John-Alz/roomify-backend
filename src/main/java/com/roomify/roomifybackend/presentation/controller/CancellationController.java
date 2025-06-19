package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.ICancellationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cancellations")
public class CancellationController {

    private final ICancellationService cancellationService;

    public CancellationController(ICancellationService cancellationService) {
        this.cancellationService = cancellationService;
    }

    @PostMapping
    public ResponseEntity<SaveResponse> saveCancellation(@RequestBody SaveCancellationRequest cancellationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cancellationService.saveCancellation(cancellationRequest));
    }

}
