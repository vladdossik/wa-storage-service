package org.wa.storage.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wa.storage.service.dto.CombinedDashboardDto;
import org.wa.storage.service.enumeration.Bucket;
import org.wa.storage.service.service.DashboardService;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users/{externalId}/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public CombinedDashboardDto getDashboard(
            @PathVariable UUID externalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,
            @RequestParam(defaultValue = "DAY") Bucket bucket) {
        return dashboardService.getAggregatedData(externalId, from, to, bucket);
    }
}
