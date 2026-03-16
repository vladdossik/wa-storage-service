package org.wa.storage.service.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class UserActivityResponseDto {
    private Long id;
    private UUID externalId;
    private String eventType;
    private OffsetDateTime timestamp;
    private Double rawQuantity;
    private String rawUnit;
    private Map<String, Object> details;
}
