package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.persistence.entity.FiltersCancellations;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.CancellationResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.ICancellationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<PageResult<CancellationResponse>> getCancellations(Integer page, Integer size, boolean ordserAsc, @ModelAttribute FiltersCancellations filtersCancellations) {
        PageResult<CancellationResponse> cancellationResponsePageResult = cancellationService.getAllCancellations(page, size, ordserAsc, filtersCancellations);
        return ResponseEntity.ok(cancellationResponsePageResult);
    }

    @GetMapping("/{cancellationId}")
    public ResponseEntity<CancellationResponse> getCancelation(@PathVariable Long cancellationId) {
        CancellationResponse cancellationResponse = cancellationService.getCancellation(cancellationId);
        return ResponseEntity.ok(cancellationResponse);
    }

}
