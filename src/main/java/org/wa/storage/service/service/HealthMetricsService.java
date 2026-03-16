package org.wa.storage.service.service;

import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.HealthMetricDto;
import org.wa.storage.service.dto.HealthMetricResponseDto;
import org.wa.storage.service.enumeration.Bucket;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface HealthMetricsService {
    void saveMetricFromDto(HealthMetricDto metricDto);
    List<HealthMetricResponseDto> getMetrics(UUID externalId, OffsetDateTime from, OffsetDateTime to);
    List<AggregatedMetricDto> getAggregatedMetrics(UUID externalId, OffsetDateTime from, OffsetDateTime to, Bucket bucket);
}
