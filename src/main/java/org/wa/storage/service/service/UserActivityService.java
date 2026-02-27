package org.wa.storage.service.service;

import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.UserActivityCreateDto;
import org.wa.storage.service.dto.UserActivityResponseDto;

import java.time.OffsetDateTime;
import java.util.List;

public interface UserActivityService {
    UserActivityResponseDto createUserActivity(String userId, UserActivityCreateDto dto);

    List<UserActivityResponseDto> getActivities(String userId, OffsetDateTime from, OffsetDateTime to);

    List<AggregatedActivityDto> getAggregatedActivities(String userId, OffsetDateTime from, OffsetDateTime to,
                                                        String bucket);

    void deleteUserActivity(String userId, Long activityId);
}
