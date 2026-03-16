package org.wa.storage.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.HealthMetricResponseDto;
import org.wa.storage.service.enumeration.Bucket;
import org.wa.storage.service.service.HealthMetricsService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users/{externalId}/metrics")
@RequiredArgsConstructor
public class HealthMetricsController {
    private final HealthMetricsService metricsService;

    @GetMapping
    public List<HealthMetricResponseDto> getMetrics(
            @PathVariable UUID externalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
        return metricsService.getMetrics(externalId, from, to);
    }

    @GetMapping("/aggregated")
    public List<AggregatedMetricDto> getAggregatedMetrics(
            @PathVariable UUID externalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,
            @RequestParam(defaultValue = "DAY") Bucket bucket) {
        return metricsService.getAggregatedMetrics(externalId, from, to, bucket);
    }

}
