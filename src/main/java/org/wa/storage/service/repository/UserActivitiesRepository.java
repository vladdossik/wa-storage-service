package org.wa.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wa.storage.service.dto.AggregatedActivityProjection;
import org.wa.storage.service.model.CustomPrimaryKey;
import org.wa.storage.service.model.UserActivity;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface UserActivitiesRepository extends JpaRepository<UserActivity, CustomPrimaryKey> {
    List<UserActivity> findByExternalIdAndTimestampBetweenOrderByTimestampAsc(
            String externalId, OffsetDateTime start, OffsetDateTime end);

    @Modifying
    @Query("DELETE FROM UserActivity u WHERE u.id = :id AND u.externalId = :externalId")
    int deleteByIdAndExternalId(@Param("id") Long id, @Param("externalId") String externalId);

    @Query(value = """
            SELECT
                time_bucket(CAST(:bucket AS INTERVAL), timestamp) AS bucket,
                event_type AS "eventType",
                COUNT(*) AS "eventCount",
                SUM(quantity) AS "totalQuantity",
                AVG(quantity) AS "avgQuantity",
                MIN(quantity) AS "minQuantity",
                MAX(quantity) AS "maxQuantity"
            FROM user_activities
            WHERE external_id = :externalId AND timestamp BETWEEN :start AND :end
            GROUP BY bucket, event_type
            ORDER BY bucket, event_type
            """, nativeQuery = true)
    List<AggregatedActivityProjection> findAggregatedActivitiesNative(
            @Param("externalId") String externalId,
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end,
            @Param("bucket") String bucket
    );
}
