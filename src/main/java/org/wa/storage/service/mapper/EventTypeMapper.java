package org.wa.storage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wa.storage.service.dto.EventTypeInfoDto;
import org.wa.storage.service.enums.EventType;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {
    @Mapping(target = "eventType", expression = "java(eventType.name())")
    @Mapping(target = "allowedUnits", expression = "java(eventType.getAllowedUnits())")
    @Mapping(target = "baseUnit", expression = "java(eventType.getBaseUnit())")
    @Mapping(target = "hasValueRange", expression = "java(eventType.hasValueRange())")
    @Mapping(target = "minValue", expression = "java(eventType.hasValueRange() ? eventType.getMinValue() : null)")
    @Mapping(target = "maxValue", expression = "java(eventType.hasValueRange() ? eventType.getMaxValue() : null)")
    EventTypeInfoDto toDto(EventType eventType);
}
