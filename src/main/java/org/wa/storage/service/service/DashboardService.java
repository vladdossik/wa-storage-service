package org.wa.storage.service.service;

import org.wa.storage.service.dto.CombinedDashboardDto;

import java.time.OffsetDateTime;

public interface DashboardService {
    CombinedDashboardDto getAggregatedData(String userId, OffsetDateTime from, OffsetDateTime to, String bucket);
}
