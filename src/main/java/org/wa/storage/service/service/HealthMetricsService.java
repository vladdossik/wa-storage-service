package org.wa.storage.service.service;

import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.HealthMetricDto;
import org.wa.storage.service.dto.HealthMetricResponseDto;

import java.time.OffsetDateTime;
import java.util.List;

public interface HealthMetricsService {
    void saveMetricFromDto(HealthMetricDto metricDto);
    List<HealthMetricResponseDto> getMetrics(String userId, OffsetDateTime from, OffsetDateTime to);
    List<AggregatedMetricDto> getAggregatedMetrics(String userId, OffsetDateTime from, OffsetDateTime to, String bucket);
}
