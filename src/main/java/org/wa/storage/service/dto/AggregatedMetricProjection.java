package org.wa.storage.service.dto;

import java.time.OffsetDateTime;

public interface AggregatedMetricProjection {
    OffsetDateTime getBucket();
    Double getAvgHeartRate();
    Integer getMaxHeartRate();
    Double getAvgSteps();
    Integer getMaxSteps();
    Double getAvgSleep();
}
