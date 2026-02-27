package org.wa.storage.service.mapper;

import org.springframework.stereotype.Component;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.enums.EventType;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AggregatedActivityMapper {
    public AggregatedActivityDto toDto(Object[] row) {
        if (row == null || row.length < 7) {
            return null;
        }
        String eventTypeStr = (String) row[1];
        String unit = toUnit(eventTypeStr);
        return new AggregatedActivityDto(
                toOffsetDateTime(row[0]),
                eventTypeStr,
                unit,
                ((Number) row[2]).longValue(),
                row[3] != null ? ((Number) row[3]).intValue() : null,
                row[4] != null ? ((Number) row[4]).doubleValue() : null,
                row[5] != null ? ((Number) row[5]).intValue() : null,
                row[6] != null ? ((Number) row[6]).intValue() : null
        );
    }

    private static String toUnit(String eventTypeStr) {
        if (eventTypeStr == null) return null;
        try {
            return EventType.valueOf(eventTypeStr).getBaseUnit();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<AggregatedActivityDto> toDtoList(List<Object[]> rows) {
        if (rows == null) {
            return Collections.emptyList();
        }
        return rows.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static OffsetDateTime toOffsetDateTime(Object value) {
        if (value == null) return null;
        if (value instanceof OffsetDateTime) return (OffsetDateTime) value;
        if (value instanceof java.time.Instant i) return OffsetDateTime.ofInstant(i, ZoneOffset.UTC);
        if (value instanceof Timestamp ts) return ts.toInstant().atOffset(ZoneOffset.UTC);
        return null;
    }
}
