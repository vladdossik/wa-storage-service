package org.wa.storage.service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wa.storage.service.dto.*;
import org.wa.storage.service.mapper.AggregatedMetricMapper;
import org.wa.storage.service.mapper.HealthMetricMapper;
import org.wa.storage.service.model.HealthMetric;
import org.wa.storage.service.repository.HealthMetricsRepository;
import org.wa.storage.service.service.HealthMetricsService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealthMetricsServiceImpl implements HealthMetricsService {

    private final HealthMetricsRepository metricsRepository;
    private final HealthMetricMapper metricMapper;
    private final AggregatedMetricMapper aggregatedMetricMapper;

    @Transactional
    public void saveMetricFromDto(HealthMetricDto dto) {
        HealthMetric metric = metricMapper.toEntity(dto);
        metricsRepository.save(metric);
    }

    @Transactional(readOnly = true)
    public List<HealthMetricResponseDto> getMetrics(String userId, OffsetDateTime from, OffsetDateTime to) {
        return metricsRepository.findByUserIdAndTimestampBetweenOrderByTimestampAsc(userId, from, to)
                .stream()
                .map(metricMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AggregatedMetricDto> getAggregatedMetrics(String userId, OffsetDateTime from, OffsetDateTime to, String bucket) {
        List<Object[]> results = metricsRepository.findAggregatedMetricsNative(userId, from, to, bucket);
        return aggregatedMetricMapper.toDtoList(results);
    }
}
