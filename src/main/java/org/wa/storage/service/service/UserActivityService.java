package org.wa.storage.service.service;

import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.UserActivityCreateDto;
import org.wa.storage.service.dto.UserActivityResponseDto;
import org.wa.storage.service.enumeration.Bucket;

import java.time.OffsetDateTime;
import java.util.List;

public interface UserActivityService {
    UserActivityResponseDto createUserActivity(String externalId, UserActivityCreateDto dto);

    List<UserActivityResponseDto> getActivities(String externalId, OffsetDateTime from, OffsetDateTime to);

    List<AggregatedActivityDto> getAggregatedActivities(String externalId, OffsetDateTime from, OffsetDateTime to,
                                                        Bucket bucket);

    void deleteUserActivity(String externalId, Long activityId);
}
