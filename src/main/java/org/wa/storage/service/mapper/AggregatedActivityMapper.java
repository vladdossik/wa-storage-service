package org.wa.storage.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.AggregatedActivityProjection;
import org.wa.storage.service.enumeration.EventType;
import org.wa.storage.service.util.DoubleValueConverter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AggregatedActivityMapper {

    private final DoubleValueConverter converter;

    public AggregatedActivityDto toDto(AggregatedActivityProjection projection) {
        if (projection == null) {
            return null;
        }
        return new AggregatedActivityDto(
                toOffsetDateTime(projection.getBucket()),
                projection.getEventType(),
                toUnit(projection.getEventType()),
                projection.getEventCount(),
                projection.getTotalQuantity(),
                converter.roundToTwoDecimals(projection.getAvgQuantity()),
                projection.getMinQuantity(),
                projection.getMaxQuantity()
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

    public List<AggregatedActivityDto> toDtoList(List<AggregatedActivityProjection> projections) {
        if (projections == null) {
            return Collections.emptyList();
        }
        return projections.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .toList();
    }

    private static OffsetDateTime toOffsetDateTime(Object value) {
        if (value instanceof OffsetDateTime) return (OffsetDateTime) value;
        if (value instanceof java.time.Instant i) return OffsetDateTime.ofInstant(i, ZoneOffset.UTC);
        if (value instanceof Timestamp ts) return ts.toInstant().atOffset(ZoneOffset.UTC);
        return null;
    }
}
