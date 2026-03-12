package org.wa.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wa.storage.service.dto.AggregatedMetricProjection;
import org.wa.storage.service.model.HealthMetric;
import org.wa.storage.service.model.CustomPrimaryKey;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface HealthMetricsRepository extends JpaRepository<HealthMetric, CustomPrimaryKey> {
    boolean existsByExternalIdAndTimestamp(String externalId, OffsetDateTime timestamp);

    List<HealthMetric> findByExternalIdAndTimestampBetweenOrderByTimestampAsc(
            String externalId, OffsetDateTime start, OffsetDateTime end);

    @Query(value = """
            SELECT time_bucket(CAST(:bucket AS INTERVAL), timestamp) AS bucket,
                   AVG(heart_rate) AS avg_heart_rate,
                   MAX(heart_rate) AS max_heart_rate,
                   AVG(steps) AS avg_steps,
                   MAX(steps) AS max_steps,
                   AVG(sleep_hours) AS avg_sleep
            FROM health_metrics
            WHERE external_id = :externalId
              AND timestamp BETWEEN :start AND :end
            GROUP BY bucket
            ORDER BY bucket
            """, nativeQuery = true)
    List<AggregatedMetricProjection> findAggregatedMetricsNative(
            @Param("externalId") String externalId,
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end,
            @Param("bucket") String bucket
    );
}
