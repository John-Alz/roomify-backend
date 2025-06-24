package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.response.MetricsResponse;
import com.roomify.roomifybackend.services.interfaces.IMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
public class MertricsController {

    private final IMetricsService metricsService;

    @GetMapping
    public ResponseEntity<MetricsResponse> getMetrics() {
        MetricsResponse metricsResponse = metricsService.getMetrics();
        return ResponseEntity.ok(metricsResponse);
    }

}
