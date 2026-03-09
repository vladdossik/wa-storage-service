package org.wa.storage.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.wa.storage.service.enumeration.EventType;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_activities")
@IdClass(CustomPrimaryKey.class)
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_activities_id_seq")
    @SequenceGenerator(name = "user_activities_id_seq", sequenceName = "user_activities_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Id
    @Column(name = "timestamp")
    private OffsetDateTime timestamp;

    @Column(name = "raw_quantity")
    private Double rawQuantity;

    @Column(name = "raw_unit")
    private String rawUnit;

    @Column(name = "quantity")
    private Integer quantity;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "details", columnDefinition = "jsonb")
    private Map<String, Object> details;
}
