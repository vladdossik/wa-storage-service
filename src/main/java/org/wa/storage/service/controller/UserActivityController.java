package org.wa.storage.service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wa.storage.service.dto.AggregatedActivityDto;
import org.wa.storage.service.dto.UserActivityCreateDto;
import org.wa.storage.service.dto.UserActivityResponseDto;
import org.wa.storage.service.enumeration.Bucket;
import org.wa.storage.service.service.UserActivityService;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/users/{userId}/activities")
@RequiredArgsConstructor
public class UserActivityController {
    private final UserActivityService userActivityService;

    @PostMapping
    public UserActivityResponseDto createActivity(
            @PathVariable String userId,
            @Valid @RequestBody UserActivityCreateDto activityDto) {
        return userActivityService.createUserActivity(userId, activityDto);
    }

    @GetMapping
    public List<UserActivityResponseDto> getActivities(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
        return userActivityService.getActivities(userId, from, to);
    }

    @GetMapping("/aggregated")
    public List<AggregatedActivityDto> getAggregatedActivities(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,
            @RequestParam(defaultValue = "DAY") Bucket bucket) {
        return userActivityService.getAggregatedActivities(userId, from, to, bucket);
    }

    @DeleteMapping("/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivity(
            @PathVariable String userId,
            @PathVariable Long activityId) {
        userActivityService.deleteUserActivity(userId, activityId);
    }
}
