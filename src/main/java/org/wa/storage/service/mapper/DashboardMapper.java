package org.wa.storage.service.mapper;

import org.springframework.stereotype.Component;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.CombinedDashboardDto;

import java.util.List;

@Component
public class DashboardMapper {

    public CombinedDashboardDto toDto(List<AggregatedMetricDto> metrics, List<AggregatedActivityDto> activities) {
        CombinedDashboardDto dto = new CombinedDashboardDto();
        dto.setMetrics(metrics);
        dto.setActivities(activities);
        return dto;
    }
}
