package org.wa.storage.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
public class UserActivityCreateDto {
    @NotBlank
    private String eventType;

    private OffsetDateTime timestamp;
    private Double rawQuantity;
    private String rawUnit;
    private Map<String, Object> details;
}
