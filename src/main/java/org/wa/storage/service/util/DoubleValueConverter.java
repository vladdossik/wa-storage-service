package org.wa.storage.service.util;

import org.springframework.stereotype.Component;

@Component
public class DoubleValueConverter {
    public Double roundToTwoDecimals(Double value) {
        if (value == null) return null;
        return Math.round(value * 100.0) / 100.0;
    }
}
