package org.wa.storage.service.mapper;

import org.springframework.stereotype.Component;
import org.wa.storage.service.dto.AggregatedMetricDto;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AggregatedMetricMapper {
    public AggregatedMetricDto toDto(Object[] row) {
        if (row == null || row.length < 6) {
            return null;
        }
        return new AggregatedMetricDto(
                toOffsetDateTime(row[0]),
                row[1] != null ? ((Number) row[1]).doubleValue() : null,
                row[2] != null ? ((Number) row[2]).intValue() : null,
                row[3] != null ? ((Number) row[3]).doubleValue() : null,
                row[4] != null ? ((Number) row[4]).intValue() : null,
                row[5] != null ? ((Number) row[5]).doubleValue() : null
        );
    }

    private static OffsetDateTime toOffsetDateTime(Object value) {
        if (value == null) return null;
        if (value instanceof OffsetDateTime) return (OffsetDateTime) value;
        if (value instanceof Timestamp ts) return ts.toInstant().atOffset(ZoneOffset.UTC);
        if (value instanceof java.time.Instant i) return i.atOffset(ZoneOffset.UTC);
        return null;
    }

    public List<AggregatedMetricDto> toDtoList(List<Object[]> rows) {
        if (rows == null) {
            return Collections.emptyList();
        }
        return rows.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
