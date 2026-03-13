package org.wa.storage.service.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class HealthMetricResponseDto {
    private Long id;
    private UUID externalId;
    private OffsetDateTime timestamp;
    private Integer heartRate;
    private Integer steps;
    private Double sleepHours;
    private Map<String, Object> metadata;
}
