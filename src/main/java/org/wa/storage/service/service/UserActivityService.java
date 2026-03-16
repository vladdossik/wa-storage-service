package org.wa.storage.service.service;

import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.UserActivityCreateDto;
import org.wa.storage.service.dto.UserActivityResponseDto;
import org.wa.storage.service.enumeration.Bucket;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface UserActivityService {
    UserActivityResponseDto createUserActivity(UUID externalId, UserActivityCreateDto dto);

    List<UserActivityResponseDto> getActivities(UUID externalId, OffsetDateTime from, OffsetDateTime to);

    List<AggregatedActivityDto> getAggregatedActivities(UUID externalId, OffsetDateTime from, OffsetDateTime to,
                                                        Bucket bucket);

    void deleteUserActivity(UUID externalId, Long activityId);
}
