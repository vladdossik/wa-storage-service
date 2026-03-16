package org.wa.storage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class EventTypeInfoDto {
    private String eventType;
    private Set<String> allowedUnits;
    private String baseUnit;
    private boolean hasValueRange;
    private Integer minValue;
    private Integer maxValue;
}
