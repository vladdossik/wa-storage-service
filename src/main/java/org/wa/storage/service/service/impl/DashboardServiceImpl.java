package org.wa.storage.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.CombinedDashboardDto;
import org.wa.storage.service.mapper.DashboardMapper;
import org.wa.storage.service.service.DashboardService;
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

    public CombinedDashboardDto getAggregatedData(String userId, OffsetDateTime from, OffsetDateTime to, String bucket) {
        List<AggregatedMetricDto> metrics = healthMetricsService.getAggregatedMetrics(userId, from, to, bucket);
        List<AggregatedActivityDto> activities = userActivityService.getAggregatedActivities(userId, from, to, bucket);
        return dashboardMapper.toDto(metrics, activities);
    }
}
