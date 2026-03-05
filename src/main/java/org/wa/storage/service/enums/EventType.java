package org.wa.storage.service.enums;

import lombok.Getter;
import org.wa.storage.service.dto.EventTypeInfoDto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
public enum EventType {
    RUNNING(Set.of("min", "h", "d"), "min"),
    JOGGING(Set.of("min", "h", "d"), "min"),
    WALKING(Set.of("min", "h", "d"), "min"),
    WORKOUT(Set.of("min", "h", "d"), "min"),
    ILLNESS(Set.of("min", "h", "d"), "d"),

    ALCOHOL_CONSUMPTION(Set.of("ml", "l"), "ml"),

    CAFFEINE_INTAKE(Set.of("mg", "g"), "mg"),

    MANUAL_MOOD(Set.of("mark"), "mark", 1, 5);

    private final Set<String> allowedUnits;
    private final String baseUnit;
    private final Integer minValue;
    private final Integer maxValue;

    EventType(Set<String> allowedUnits, String baseUnit) {
        this(allowedUnits, baseUnit, null, null);
    }

    EventType(Set<String> allowedUnits, String baseUnit, Integer minValue, Integer maxValue) {
        this.allowedUnits = allowedUnits;
        this.baseUnit = baseUnit;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public boolean hasValueRange() {
        return minValue != null && maxValue != null;
    }

    public int getMinValue() {
        if (minValue == null) {
            throw new UnsupportedOperationException("This event type does not have a value range");
        }
        return minValue;
    }

    public int getMaxValue() {
        if (maxValue == null) {
            throw new UnsupportedOperationException("This event type does not have a value range");
        }
        return maxValue;
    }

    public EventTypeInfoDto toInfoDto() {
        return new EventTypeInfoDto(
                name(),
                getAllowedUnits(),
                getBaseUnit(),
                hasValueRange(),
                hasValueRange() ? getMinValue() : null,
                hasValueRange() ? getMaxValue() : null
        );
    }

    private static final List<EventTypeInfoDto> ALL_AS_INFO =
            Arrays.stream(values()).map(EventType::toInfoDto).toList();

    public static List<EventTypeInfoDto> getAllAsInfo() {
        return ALL_AS_INFO;
    }
}
