package org.wa.storage.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CombinedDashboardDto {
    private List<AggregatedMetricDto> metrics;
    private List<AggregatedActivityDto> activities;
}
