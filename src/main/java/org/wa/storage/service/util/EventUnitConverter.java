package org.wa.storage.service.util;

import org.springframework.stereotype.Component;
import org.wa.storage.service.enumeration.EventType;

import java.util.Map;

@Component
public class EventUnitConverter {
    private static final Map<String, Integer> FACTORS = Map.of(
            "min", 1,
            "h", 60,
            "d", 1440,
            "mg", 1,
            "g", 1000,
            "kg", 1000000,
            "ml", 1,
            "l", 1000,
            "mark", 1
    );

    public int convertToBase(double rawValue, String unit, EventType eventType) {
        if (!eventType.getAllowedUnits().contains(unit)) {
            throw new IllegalArgumentException("Единица измерения " + unit + " не подходит для типа " + eventType);
        }
        if (eventType.hasValueRange()) {
            if (rawValue < eventType.getMinValue() || rawValue > eventType.getMaxValue() || rawValue != Math.floor(rawValue)) {
                throw new IllegalArgumentException(
                        "Значение должно быть целым числом " + eventType.getMinValue() +
                                "-" + eventType.getMaxValue() + " для " + eventType);
            }
            }
        Integer factor = FACTORS.get(unit);
        if (factor == null) {
            throw new IllegalArgumentException("Неизвестная единица измерения: " + unit);
        }
        return (int) Math.round(rawValue * factor);
    }
}
