package org.wa.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wa.storage.service.model.UserActivity;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface UserActivitiesRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByUserIdAndTimestampBetweenOrderByTimestampAsc(
            String userId, OffsetDateTime start, OffsetDateTime end);

    @Modifying
    @Query("DELETE FROM UserActivity u WHERE u.id = :id AND u.userId = :userId")
    int deleteByIdAndUserId(@Param("id") Long id, @Param("userId") String userId);

    @Query(value = """
            SELECT
                time_bucket(CAST(:bucket AS INTERVAL), timestamp) AS bucket,
                event_type,
                COUNT(*) AS event_count,
                SUM(quantity) AS total_quantity,
                AVG(quantity) AS avg_quantity,
                MIN(quantity) AS min_quantity,
                MAX(quantity) AS max_quantity
            FROM user_activities
            WHERE user_id = :userId AND timestamp BETWEEN :start AND :end
            GROUP BY bucket, event_type
            ORDER BY bucket, event_type
            """, nativeQuery = true)
    List<Object[]> findAggregatedActivitiesNative(
            @Param("userId") String userId,
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end,
            @Param("bucket") String bucket
    );
}
