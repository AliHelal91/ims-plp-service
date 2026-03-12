package com.channels.ims.plp.entity.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "synced_models", schema = "ims_plp_service_db")
public class SyncedModels {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "synced_models_gen")
    @SequenceGenerator(name = "synced_models_gen", sequenceName = "synced_models_seq",
            allocationSize = 1, schema = "ims_plp_service_db")
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "model_id", nullable = false)
    private Integer modelId;  // Same as Model id in ims-product-service

    @Column(name = "model_code", nullable = false)
    private String modelCode; // Same as Model Code in ims-product-service

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;
}
