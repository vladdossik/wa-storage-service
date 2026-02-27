package org.wa.storage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wa.storage.service.dto.HealthMetricDto;
import org.wa.storage.service.dto.HealthMetricResponseDto;
import org.wa.storage.service.model.HealthMetric;

@Mapper(componentModel = "spring")
public interface HealthMetricMapper {
    HealthMetricResponseDto toResponseDto(HealthMetric metric);

    @Mapping(target = "id", ignore = true)
    HealthMetric toEntity(HealthMetricDto dto);
}
