package org.wa.storage.service.util;

import org.springframework.stereotype.Component;
import org.wa.storage.service.enums.EventType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EventUnitConverter {
    private static final Map<String, Integer> FACTORS = new HashMap<>();

    static {
        FACTORS.put("min", 1);
        FACTORS.put("h", 60);
        FACTORS.put("d", 1440);
        FACTORS.put("mg", 1);
        FACTORS.put("g", 1000);
        FACTORS.put("kg", 1000000);
        FACTORS.put("ml", 1);
        FACTORS.put("l", 1000);
        FACTORS.put("mark", 1);
    }

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
