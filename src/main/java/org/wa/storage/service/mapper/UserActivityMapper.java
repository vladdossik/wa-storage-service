package org.wa.storage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wa.storage.service.dto.UserActivityCreateDto;
import org.wa.storage.service.dto.UserActivityResponseDto;
import org.wa.storage.service.model.UserActivity;

@Mapper(componentModel = "spring")
public interface UserActivityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    UserActivity toEntity(UserActivityCreateDto dto);

    UserActivityResponseDto toDto(UserActivity userActivity);
}
