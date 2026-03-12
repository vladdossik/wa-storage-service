package org.wa.storage.service.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
public class UserActivityResponseDto {
    private Long id;
    private String externalId;
    private String eventType;
    private OffsetDateTime timestamp;
    private Double rawQuantity;
    private String rawUnit;
    private Map<String, Object> details;
}
