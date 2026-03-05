package org.wa.storage.service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.UserActivityCreateDto;
import org.wa.storage.service.dto.UserActivityResponseDto;
import org.wa.storage.service.enums.EventType;
import org.wa.storage.service.exception.ActivityNotFoundException;
import org.wa.storage.service.mapper.AggregatedActivityMapper;
import org.wa.storage.service.mapper.UserActivityMapper;
import org.wa.storage.service.model.UserActivity;
import org.wa.storage.service.repository.UserActivitiesRepository;
import org.wa.storage.service.service.UserActivityService;
import org.wa.storage.service.util.EventUnitConverter;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivitiesRepository activitiesRepository;
    private final UserActivityMapper activityMapper;
    private final EventUnitConverter unitConverter;
    private final AggregatedActivityMapper aggregatedActivityMapper;

    @Override
    @Transactional
    public UserActivityResponseDto createUserActivity(String userId, UserActivityCreateDto dto) {
        EventType eventType = EventType.valueOf(dto.getEventType());

        Integer quantity = null;
        if (dto.getRawQuantity() != null && dto.getRawUnit() != null) {
            quantity = unitConverter.convertToBase(dto.getRawQuantity(), dto.getRawUnit(), eventType);
        }
        UserActivity activity = activityMapper.toEntity(dto);
        activity.setUserId(userId);
        activity.setQuantity(quantity);
        UserActivity savedActivity = activitiesRepository.save(activity);
        log.info("Created user activity: id={}, userId={}, type={}",
                savedActivity.getId(), userId, eventType);
        return activityMapper.toDto(savedActivity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserActivityResponseDto> getActivities(String userId, OffsetDateTime from, OffsetDateTime to) {
        return activitiesRepository.findByUserIdAndTimestampBetweenOrderByTimestampAsc(userId, from, to)
                .stream().map(activityMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AggregatedActivityDto> getAggregatedActivities(
            String userId, OffsetDateTime from, OffsetDateTime to, String bucket) {
        List<Object[]> results = activitiesRepository.findAggregatedActivitiesNative(userId, from, to, bucket);
        return aggregatedActivityMapper.toDtoList(results);
    }

    @Override
    @Transactional
    public void deleteUserActivity(String userId, Long activityId) {
        int deleted = activitiesRepository.deleteByIdAndUserId(activityId, userId);
        if (deleted == 0) {
            throw new ActivityNotFoundException("Запись не найдена или не пренадлежит пользователю");
        }
    }
}
