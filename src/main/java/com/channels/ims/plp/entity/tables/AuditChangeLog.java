package com.channels.ims.plp.entity.tables;

import com.channels.ims.plp.enums.ChangeAction;
import com.channels.ims.plp.enums.EntityTypeEnums;
import com.channels.ims.plp.enums.SyncErrorTypeEnums;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "audit_change_log", schema = "ims_plp_service_db")
public class AuditChangeLog {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityTypeEnums entityType;

    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private ChangeAction action;

    @Column(name = "change_reason", nullable = false)
    private String changeReason;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "changed_fields", columnDefinition = "jsonb")
    private String changedFields;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "before_state", columnDefinition = "jsonb",nullable = false)
    private String beforeState;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "after_status", columnDefinition = "jsonb",nullable = false)
    private String afterStatus;

    @Column(name = "sync_batch_id", nullable = false)
    private UUID syncBatchId;

    @ManyToOne
    @JoinColumn(name = "request_id",referencedColumnName = "id")
    private Request requestId;

    @Column(name = "triggered_by", nullable = false)
    private String triggeredBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "error_type")
    private SyncErrorTypeEnums errorType;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_message")
    private String errorMessage;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
