package org.wa.storage.service.dto;

import java.time.Instant;

public interface AggregatedActivityProjection {
    Instant getBucket();
    String getEventType();
    Long getEventCount();
    Integer getTotalQuantity();
    Double getAvgQuantity();
    Integer getMinQuantity();
    Integer getMaxQuantity();
}
