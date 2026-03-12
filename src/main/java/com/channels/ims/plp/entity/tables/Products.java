package com.channels.ims.plp.entity.tables;

import com.channels.ims.plp.enums.SyncStatusEnums;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "products", schema = "ims_plp_service_db")
public class Products {


    // 9511008839
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "product_id",nullable = false,unique = true)
    private  Integer productId;

    @Column(name = "item_code", nullable = false, unique = true)
    private String itemCode;

    @Column(name = "name_en", nullable = false)
    private String nameEN;

    @Column(name = "name_ar")
    private String nameAR;

    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    @Column(name = "model_code", nullable = false)
    private String modelCode;

    @Column(name = "description_en")
    private String descriptionEN;

    @Column(name = "description_ar")
    private String descriptionAR;

    @Column(name = "product_category", nullable = false)
    private String productCategory;

    @Column(name = "product_sub_category")
    private String productSubCategory;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes", columnDefinition = "jsonb")
    private JsonNode attributes;

    @Enumerated(EnumType.STRING)
    @Column(name = "sync_status", nullable = false)
    private SyncStatusEnums syncStatus;

    @Column(name = "product_version", nullable = false, columnDefinition = "integer default 1")
    private Integer productVersion;

    @Column(name = "synced_version", nullable = false, columnDefinition = "integer default 0")
    private Integer syncedVersion;

    @Column(name = "last_synced_at")
    private LocalDateTime lastSyncedAt;

    @Column(name = "last_sync_attempt_at")
    private LocalDateTime lastSyncAttemptAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "sales_channels", columnDefinition = "jsonb", nullable = false)
    private List<String> salesChannels;

    @Column(name = "is_preorder", nullable = false,columnDefinition = "Boolean default False")
    private Boolean isPreorder;

    @Column(name = "is_serialized", nullable = false,columnDefinition = "Boolean default False")
    private Boolean isSerialized;

    @Column(name = "returnAllowed", nullable = false,columnDefinition = "Boolean default False")
    private Boolean return_allowed;

    @Column(name = "free_shipping_enabled", nullable = false,columnDefinition = "Boolean default False")
    private Boolean freeShippingEnabled;

    @Column(name = "inventory_check", nullable = false,columnDefinition = "Boolean default True")
    private Boolean inventoryCheck;

    @Column(name = "allow_non_stc", nullable = false,columnDefinition = "Boolean default True")
    private Boolean allowNonStc;

    @Column(name = "publish_date", nullable = false)
    private LocalDateTime publishDate;

    @Column(name = "available_date", nullable = false)
    private LocalDateTime availableDate;

    @Column(name = "discontinued_date", nullable = false)
    private LocalDateTime discontinuedDate;

    @Column(name = "preorder_date", nullable = false)
    private LocalDateTime preorderDate;


}
