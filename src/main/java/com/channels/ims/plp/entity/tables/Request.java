package com.channels.ims.plp.entity.tables;

import com.channels.ims.plp.enums.RequestType;
import com.channels.ims.plp.enums.SyncErrorTypeEnums;
import com.channels.ims.plp.enums.SyncStatusEnums;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "request", schema = "ims_plp_service_db")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_gen")
    @SequenceGenerator(name = "request_gen", sequenceName = "request_seq",
            allocationSize = 1, schema = "ims_plp_service_db")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ims_product_id",unique = true,nullable = false)
    private UUID imsProductId;

    @Column(name = "sync_batch_id")
    private String syncBatchId;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", nullable = false)
    private RequestType requestType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "request_payload",nullable = false, columnDefinition = "jsonb")
    private String requestPayload;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "response_payload", columnDefinition = "jsonb")
    private String responsePayload;

    @Column(name = "parent_history_id")
    private String parentHistoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SyncStatusEnums status;

    @Column(name = "error_type")
    private String errorType;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "retry_count", nullable = false, columnDefinition = "integer default 0")
    private Integer retryCount;

    @Column(name = "max_retries", nullable = false, columnDefinition = "integer default 3")
    private Integer maxRetries;

    @Column(name = "next_retry_at")
    private LocalDateTime nextRetryAt;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
