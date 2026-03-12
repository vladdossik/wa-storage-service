package org.wa.storage.service.service;

import org.wa.storage.service.dto.CombinedDashboardDto;
import org.wa.storage.service.enumeration.Bucket;

import java.time.OffsetDateTime;

public interface DashboardService {
    CombinedDashboardDto getAggregatedData(String externalId, OffsetDateTime from, OffsetDateTime to, Bucket bucket);
}
