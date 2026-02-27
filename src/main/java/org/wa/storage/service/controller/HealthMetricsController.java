package org.wa.storage.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.wa.storage.service.dto.*;
import org.wa.storage.service.service.HealthMetricsService;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/users/{userId}/metrics")
@RequiredArgsConstructor
public class HealthMetricsController {
    private final HealthMetricsService metricsService;

    @GetMapping
    public List<HealthMetricResponseDto> getMetrics(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
        return metricsService.getMetrics(userId, from, to);
    }

    @GetMapping("/aggregated")
    public List<AggregatedMetricDto> getAggregatedMetrics(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,
            @RequestParam(defaultValue = "1 day") String bucket
    ) {
        return metricsService.getAggregatedMetrics(userId, from, to, bucket);
    }

}
