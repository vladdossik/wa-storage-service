package org.wa.storage.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.CombinedDashboardDto;
import org.wa.storage.service.mapper.DashboardMapper;
import org.wa.storage.service.service.DashboardService;
import org.wa.storage.service.enumeration.Bucket;
import org.wa.storage.service.service.HealthMetricsService;
import org.wa.storage.service.service.UserActivityService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final HealthMetricsService healthMetricsService;
    private final UserActivityService userActivityService;
    private final DashboardMapper dashboardMapper;

    @Override
    @Transactional
    public CombinedDashboardDto getAggregatedData(String externalId, OffsetDateTime from, OffsetDateTime to, Bucket bucket) {
        List<AggregatedMetricDto> metrics = healthMetricsService.getAggregatedMetrics(externalId, from, to, bucket);
        List<AggregatedActivityDto> activities = userActivityService.getAggregatedActivities(externalId, from, to, bucket);
        return dashboardMapper.toDto(metrics, activities);
    }
}
