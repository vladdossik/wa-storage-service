package org.wa.storage.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.wa.storage.service.dto.AggregatedMetricDto;
import org.wa.storage.service.dto.AggregatedMetricProjection;
import org.wa.storage.service.util.DoubleValueConverter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AggregatedMetricMapper {
    private final DoubleValueConverter converter;

    public AggregatedMetricDto toDto(AggregatedMetricProjection projection) {
        if (projection == null) {
            return null;
        }
        return new AggregatedMetricDto(
                projection.getBucket(),
                converter.roundToTwoDecimals(projection.getAvgHeartRate()),
                projection.getMaxHeartRate(),
                converter.roundToTwoDecimals(projection.getAvgSteps()),
                projection.getMaxSteps(),
                converter.roundToTwoDecimals(projection.getAvgSleep())
        );
    }

    public List<AggregatedMetricDto> toDtoList(List<AggregatedMetricProjection> projections) {
        if (projections == null) {
            return Collections.emptyList();
        }
        return projections.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .toList();
    }
}
