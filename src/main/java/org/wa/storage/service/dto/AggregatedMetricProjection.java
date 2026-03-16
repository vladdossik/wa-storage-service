package org.wa.storage.service.dto;

import java.time.Instant;

public interface AggregatedMetricProjection {
    Instant getBucket();
    Double getAvgHeartRate();
    Integer getMaxHeartRate();
    Double getAvgSteps();
    Integer getMaxSteps();
    Double getAvgSleep();
}
